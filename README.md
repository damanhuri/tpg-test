# tpg-test
TPG test exercises

Test #1
- read list of ip addresses from file ip_addresses.txt located at the root of project path
- validate each of the ip addresses line by line and display the list of valid ip addresses and subsequently display the invalid ip addresses

Test #2
- StudentSorter class will use Student pojo object which implement the Comparable to be used with Collections sort API.
- output will be sorted first by GPA descending then by name in alphabetical order and lastly by student's ID

Test #3
- this tool will do measurement of time needed to execute a method ( callMethodToMeasure() as in the example )
- arguments of the tool are {no. of threads} {no. of calls to method} {timeout value in seconds}
- no. of threads : to define number of threads will be initialized in the pool in order for the measurement execution to run concurrently
- no. of calls to method : to define how many tests will be executed to get minimum/maximum/average time of method's execution
- timeout : to define the max seconds for the test to be considered as timeout/error, execution time will not be accumulated in total execution time
