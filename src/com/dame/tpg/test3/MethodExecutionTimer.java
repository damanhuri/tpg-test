package com.dame.tpg.test3;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MethodExecutionTimer {
	
	/**
	 * Run this class with arguments eg.
	 * 
	 * {no. of threads} {no. of calls to method} {timeout value in seconds}
	 * 4 50 10
	 */

	private static int numOfThreads = 1;
	private static int numOfRepeats = 1;
	
	// in miliseconds
	private static int TIMEOUT = 5000;
	
	public static void main(String[] args) {

		ExecutorService executor = null;
		try {
			
			validateArgs(args);

			if (args.length >= 1) {
				numOfThreads = Integer.parseInt(args[0]);
			}

			if (args.length >= 2) {
				numOfRepeats = Integer.parseInt(args[1]);	
			}
			
			if (args.length >= 3) {
				TIMEOUT = Integer.parseInt(args[2]) * 1000;	
			}
						
			System.out.println("Number of threads : " + numOfThreads);
			System.out.println("Number of repeats : " + numOfRepeats);

			executor = Executors.newFixedThreadPool(numOfThreads);
			CompletionService<Status> completionService = new ExecutorCompletionService<Status>(executor);

			int repeat = 0;
			while (repeat < numOfRepeats) {
				completionService.submit(new TimerCallable());
				repeat++;
			}

			long min = 0;
			long max = 0;
			long totalDuration = 0;
			long numOfSuccess = 0;
			long numOfTimeout = 0;
			for (int i = 0; i < numOfRepeats; i++) {
				try {
					Future<Status> result = completionService.take();
					Status status = result.get();
					
					if (status.totalTime == TIMEOUT) {
						numOfTimeout++;
					}
					else {
						
						if (status.totalTime <= TIMEOUT) {
							totalDuration = totalDuration + status.totalTime;
							
							if (i == 0) {
								min = status.totalTime;
								max = status.totalTime;
							}
							
							if (status.totalTime > max) {
								max = status.totalTime;
							}
							
							if (status.totalTime < min) {
								min = status.totalTime;
							}
							
							System.out.println("Callable execution completed. status: " + status.toString());
							
							numOfSuccess++;
						}
						else {
							numOfTimeout++;
						}
						
					}
	

				} catch (InterruptedException | ExecutionException e) {
					System.out.println("Callable execution returned error!");
				}
			}
			
			long avgExecTime = numOfSuccess == 0 ? 0 : totalDuration/numOfSuccess;
			System.out.println("Min. execution time = "+min
					+", Max. execution time = " + max
					+", Average execution time = " + avgExecTime 
					+", No. of timeouts/errors = " + numOfTimeout 
					+ ", No. of success execution = " + numOfSuccess);

		} finally {
			// shut down the executor service now
			if (null != executor) { executor.shutdown(); }
			
			System.out.println("Program terminated.");
		}
		
	}
	
	private static void validateArgs(String[] args) {
		
		if (args.length > 2) {
			throw new IllegalArgumentException("Maximum number arguments exceeded!");
		}
		
		if (numOfThreads < 1) {
			throw new IllegalArgumentException("Number of threads must be at least 1 !");
		}
		
		if (numOfRepeats < 1) {
			throw new IllegalArgumentException("Number of repeats must be at least 1 !");
		}
	}
	
	private static class TimerCallable implements Callable<Status> {

		@Override
		public Status call() throws Exception {

			Status status = new Status();		
			
			try {

				status.totalTime = callMethodToMeasure();
				
			} catch (Exception e) {		
				e.printStackTrace();
				status.totalTime = TIMEOUT;
			}

			return status;
		}

	}
	

	private static class Status {
		public long totalTime;

		@Override
		public String toString() {
			return new StringBuilder().append(" totalTime=").append(totalTime)
										.toString();
		}
	}
	
	private static long callMethodToMeasure() {

		long startTime = System.currentTimeMillis();
		
		// call the method to measure
		TestClass testClass = new TestClass();
		testClass.testMethod();
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		return totalTime;
	}

}
