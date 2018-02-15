# Project 1

The description for this project can be found on Canvas. For convenience, the description is also listed here.

## Description

Your first programming project is to implement k-way merge sort for any `k>=2`, using *only* int arrays. You may *not* use Lists, ArrayLists, or Collections.  You may use Arrays class to print the content of the array. For the full credit, you may *not* create more than one int array. In your source code, _explain how you implemented k-way merge in comments_ right above your merge method. Submission is through your github repository.

## Input

The input is given as a text file and will contain:

- k (for k-way merge sort)
- list of numbers separated by whitespace (space, tab, newline)

The example [input file](https://usfca.instructure.com/courses/1575502/files/64410999/download?verifier=024IF6hLH9mHZPgcrWJSeLOh9f0STF8eudOGqJZJ&wrap=1 "numbers") is numbers for 5-way merge sort.

> **Note:** The first number in the numbers file is your *k* or how many ways your merge sort will do.

**Note:** The text file will be provided as a command line argument. For example: `java project1 numbers`.


## Output

The output should be also a file in the format of

- the numbers sorted in an ascending order

and the matching example output file for numbers is [numbers_sorted](https://usfca.instructure.com/courses/1575502/files/64411005/download?verifier=JpBUdWGkFrqFyhrebGOwQO9pt5m2WCfOP4cXBG0C&wrap=1 "numbers_sorted"). Note that the name of the output file is *\<name of the input file>***_sorted**.

## Additional Notes and Requirements

For any input size that is *not* divisible by k, you will split the list to (k-1) lists that contain (n/k) elements and 1 list for the rest of the elements. For example, if the input size is 11 and k is 5, then the list will be split into (2, 2, 2, 2, 3) elements. 

> **Note:** If the input size is 14 and k is 5, then the list will be split into (2, 2, 2, 2, 6) elements.

Your program should print the array at the end of every merge method call. In other words, if the input is [4, 2, 6, 1] for 2-way merge, then the program should print:

	[2, 4, 6, 1]

	[2, 4, 1, 6]
	
	[1, 2, 4, 6]
	
## Submission Details

Upload your file(s) to your github repository. Your repository should look like: `usfcs245/project1-username`. You only need to upload the files for your merge. For example, if your program files are called KMerge.java and Driver.java, you only need to upload these two. 

**Notes:** If you are using eclipse, make sure you either use a **default** package *or* to **remove** the package line before submitting.
