package com.dame.tpg.test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddrValidator {
	
	private static final String IPADDRESS_REGEX_PATTERN = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";

	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {

		List<String> ipAddresses = getListOfIpAddresses();

		Pattern pattern = Pattern.compile(IPADDRESS_REGEX_PATTERN);
		
		List<String> validIps = new ArrayList<>();
		List<String> invalidIps = new ArrayList<>();
		
		for (String ipAddr : ipAddresses) {
			Matcher matcher = pattern.matcher(ipAddr);
			if (matcher.find()) {
				validIps.add(ipAddr);
				
			} else{
				invalidIps.add(ipAddr);
			}
		}
		
		System.out.println("List of valid IP addresses :");
		for (String string: validIps) {
			System.out.println(string);
		}
		
		System.out.println("\n\nList of invalid IP addresses :");
		for (String string: invalidIps) {
			System.out.println(string);
		}
		
	}
	
	private static List<String> getListOfIpAddresses() throws FileNotFoundException, URISyntaxException {
		String thisPath = IpAddrValidator.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		String parentPath = new File(thisPath).getParent();
		String filePath = parentPath + "/ip_addresses.txt";
		
		System.out.println("Path : " + filePath);
		
		Scanner s = new Scanner(new File(filePath));
		List<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		return list;
	}

}
