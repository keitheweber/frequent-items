# Frequent-Items

Based	on	“Finding	Frequent	Itemsets	and	Association	Rules”
** Problem	1	(5	points):	A-Priori	Algorithm **
Write	a program	using	any	programing	language	of	your	choice	(C/C++,	Python,	Java,	MatLab,	R,
etc.)	to	find	frequent	pairs for an	input transaction/purchase records	(market	basket	model).	You
must	use	A-Priori	Algorithm discussed	in	the	class.	For	the	support	s: we	will	vary	this	parameter
to	see	how	many	frequent	pairs	we	get	for	each	value	of	s.	Use	these	values	for	s:	2,	5,	10,	20,
50.
Requirements:
1. Generate	an	input	file:	Using	a	random	number	generator,	generate	purchase	records	and
write	them	in	the	input	file.	Make	sure	that	your	file	has	at	least	1000 different	baskets
(purchases/transaction	records).	Each	basket	should	contain	at	least	10	items	(denoted
by	integer	IDs).	Your	entire	file	may	contain	2000	distinct	items.	Now,	for	each	basket:
generate	five	random	numbers	between	1	and	100,	and	another	5	numbers	between	101
and	2000.	The	generated	numbers	will	be	item	IDs	for	the	corresponding	basket.	You	may
use	a	marker	(symbol) to	separate	consecutive	basketsin	the	file,	or	simply	you	may	store
each	basket	in	a	separate line. For	example,	your	input	 file	might	look	like	 this (first	2
lines):
3,10,54,66,7,106,608,1000,900,1608
6,90,100,33,88,1445,490,1203,1839,1111
…
2. You	must	write	the	results	in	another	output	file.	The	output	file	should	list the	frequent
pairs	for	each	value	of	s.	Also,	report	the	amount	of	memory	required	in	different	phases
of	your	algorithm and	total	execution	time.	You may	use	a	third-party	tool	to	learn	this
memory	requirement,	or	you	may	simply	compute	that	from	your	major	data	structures
(e.g.,	arrays,	hash	tables,	etc.).

  ** Problem	2	(5 points): PCY	Algorithm **
Repeat	problem	1	using	PCY	Algorithm.	Use	the	same	input	file.	Provide	a	similar	output	file to
Problem	1.	Compare	memory	requirements	of	different	phases	with	the	values	obtained	for	APriori	Algorithm.

# Submission

data/Keith_Weber_HW3.zip

# Compile

Execute the following command to build the application.

./gradlew fatJar

The executable jar will be available at build/lib/frequent-items-all-1.0-SNAPSHOT.jar

# Running the application

Command line arguments
-i input file
-s support value
-a algorithm ("APriori" or "PCY")
-g generate input file
-b number of baskets when generating input file


example:
java -jar build/lib/frequent-items-all-1.0-SNAPSHOT.fatJar -i data/input_file.txt -s 2



# Input file format

Each line should contain a comma delimited list of items as integers.

Example file contents:
30,38,62,83,89,217,427,1435,1672,1837
4,9,39,41,86,425,557,1314,1338,1863
46,67,82,88,94,260,754,790,1336,1725
20,33,34,51,72,868,1048,1904,1906,1923
12,44,50,52,71,192,329,1276,1417,1701
9,27,35,71,100,682,890,1167,1595,1701
54,56,70,71,78,151,830,1169,1744,1983
7,16,30,31,61,175,385,712,1295,1676

# Output file format

The output file contains a header consisting of the start time, end time and number of milliseconds for the runtime, followed by each frequent pair.

Example file contents:
Start Time: 2019-04-13T21:18:20.801
End Time: 2019-04-13T21:18:23.344
Total milliseconds: 2543
Pair: [1, 19]
Pair: [1, 33]
Pair: [2, 3]
Pair: [2, 23]

# Java Heap Usage

The built in java tool "hprof" was used to satisfy the profiling requirement. To use the tool add the following arguments to the java command line -agentlib:hprof=heap=all. The output of the hprof tool has been added to the zip provided in the moodle upload.

# Details

Further details are available in the logs. Logback is the log engine used by the application. By default, the log output will print to the console and print values of each iteration.
