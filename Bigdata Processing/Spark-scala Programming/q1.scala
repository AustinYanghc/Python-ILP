// 1. Spark-Scala Programming Fundamentals

//cd $SPARK_HOME
//spark-shell

/* a. Create a Spark data frame from a CSV file which has the headers in the first row
(create a small CSV file or use ~/ /Documents/Datasets/simple.csv in the bigdata
virtual machine) and verify. */
val df = spark.read.format("csv").option("header","true").load("/home/user/Documents/Datasets/simple.csv")
df.show()
//According to the question. We need to read the CSV file into Data frame with specifying “header is true”.


// b. Print the data frame’s schema.
df.printSchema()
//Here we just simply use "printSchema" to print the data frame’s schema.


// c. Convert the data frame to a RDD and display its contents.
val myRDD: org.apache.spark.rdd.RDD[org.apache.spark.sql.Row] = df.rdd
//we can change the RDD from Row to Array passing through Sequence.

myRDD.collect().foreach(println)
/* Here we use collect method to return all the contents of myRDD as an array.
Then we can use foreach methid to go through and print out all the contents of myRDD */


/* d. Create a RDD by reading from a text file (create a text file or use
$SPARK_HOME/README.md in the bigdata vm). */
val myRDD2 = sc.textFile("README.md")
/* Here we create a new RDD(Resilient Distributed Dataset) from the ‘README.md’ text file in
the Spark source directory.
‘SC’ is SparkContext instance created automatically in the spark 
shell and tells Spark how to access a cluster */

/* e. Calculate the total length in characters, including white spaces, for all the lines in
the $SPARK_HOME/README.md file. */
myRDD2.flatMap(_.toList).count()
//We can flatMap myRDD2 into separate record per character, and then use count to get the total number.


/* f. Count and display all the words as (String, Int) pairs, which occur in
$SPARK_HOME/README.md file of the bigdata vm. */

val wordCounts = myRDD2.flatMap(_.split(" ")).map(word =>
(word, 1)).reduceByKey(_ + _)
/* Here I use the Chaining technique to perform flatMap, map, and reduceByKey.
First, the flatMap method returns a new RDD formed by splitting all the words on the
basis of spaces between them. Then, the map method forms key value pairs for each 
occurence of the word we get from the flatMap result. And each word will be given a value 1.
At last, reduceByKey method will reduce each key by the number of its occurrences. 
We will get that total number of values for each word. */

wordCounts.collect().foreach(println)
/* Here I use collect method to return all the contents of myRDD2 as an array.
Then I can use foreach method to go through and print out all the contents of myRDD2 */


/* g. Write a program which does word count of the $SPARK_HOME/README.md file
using Spark. Explain the reduction operation. */

val wordCounts2 = myRDD2.flatMap(_.split(" ")).map(word =>
(word, 1)).count()
/* This question is similar with the last question. But I will only use flatMap and map 
methods here. Similarly, after we use flatMap and map methods, we will get each word with value 1.
The result is exactly the total words of myRDD2. Thus, I will use count method instead of 
reduceByKey method to get the number of total words.
*/


/* h. Factorial is an integer number calculated as the product of itself with all number
below it e.g. Factorial of 3 or 3! = 3x2x1 = 6. Factorial of 0 is always 1. Using these
rules write a compact program, which computes the factorials of an integer array
X(1,2,3,4,5) and then sums these up into a single value. */

def factorial(number:Int) : Int = {
          if (number == 1)
             return 1
          number * factorial (number - 1)
        }
// First, I define a function that can perform factorial operations.


object Test{
	def main(args: List[Int]): Unit = {
		println("The Factorial result is " + sumAll(5));
	}
	def sumAll(n:Int):Int = {
		var sum : Int = 0;
		var i = 0;
		for(i <-1 to n){
			var x = factorial(i)
			sum = sum + x;
		}
		return sum;
	}
}
/*Then I define a function to sum up the results of the output. 
The input is from 1 to 5. This function will put 1 in the factorial
function first to get the factorial result. Then it will put 2 in the 
factorial function to get the factorial result and sum with the result 
of 1. The rest of the steps are the same. Finally, We will get the result.
*/

val X = List(1,2,3,4,5)
Test.main(X)

