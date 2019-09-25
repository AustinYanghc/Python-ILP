//2. Data Exploration with Spark

/* a. Using a parquet-formatted dataset on flight data, flight_2008_pq.parquet/,
available in bigvm’s ~/Documents/Datasets/flight_2008_pq.parquet
(and also provided as flight_2008_pq.parquet.zip in Moodle), calculate and display
the maximum flight departure delays (DepDelay) for up to 20 flights. Re-arrange and
display the delays in descending order (listing the flight with the highest delays at
the top). */

val df = spark.read.parquet("/home/user/Documents/Datasets/flight_2008_pq.parquet")
//Using read funciton to load parquet-formatted dataset from its source directory and build a new DataFrame.

df.createOrReplaceTempView("flightFile")
/* Then we registered the DataFrame which is obtained from the previous step into the 
temporary table using the 'createOrReplaceTempView' method. */

val DepDelay = spark.sql("SELECT TailNum, DepDelay FROM flightFile WHERE DepDelay > 0 ORDER BY DepDelay desc LIMIT 20")
/* Then use the SQL statement to operate the temporary table. 
First, we select the 'TailNum' and 'DepDelay' from table. 
Second, we re-arrange the delays in descending order and
using 'LIMIT' to display only the first 20 records. */

DepDelay.show()
//Show the results.
