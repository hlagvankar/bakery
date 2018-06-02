# BakeryCodingChallenge

## Background

A bakery used to base the price of their produce on an individual item cost. So if a customer ordered 10
cross buns then they would be charged 10x the cost of single bun. The bakery has decided to start
selling their produce prepackaged in bunches and charging the customer on a per pack basis. So if the
shop sold vegemite scroll in packs of 3 and 5 and a customer ordered 8 they would get a pack of 3 and
a pack of 5. The bakery currently sells the following products:

| Name              | Code           | Packs       |
| -------------     | -------------- | ----------- |
| Vegemite Scroll   | VS5            |  3 @ $6.99  | 
|                   |                |  5 @ $8.99  |
| Blueberry Muffin  | VS5            |  2 @ $9.95  |
|                   |                |  5 @ $16.95 |
|                   |                |  8 @ $24.95 |
| Croissant         | VS5            |  3 @ $5.95  |
|                   |                |  5 @ $9.95  |
|                   |                |  9 @ @16.99 |   
                                      
## Task

Given a customer order you are required to determine the cost and pack breakdown for each product.
To save on shipping space each order should contain the minimal number of packs.

## Input

Each order has a series of lines with each line containing the number of items followed by the product
code. An example input:

10 VS5 

14 MB11

13 CF

## Output

A successfully passing test(s) that demonstrates the following output:

10 VS5 $17.98 <br/>
&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2 x 5 $8.99
     
14 MB11 $54.8 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1 x 8 $24.95</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3 x 2 $9.95

13 CF $25.85 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2 x 5 $9.95 <br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 1 x 3 $5.95


## Running the application  
It requires pipe separated file  
`sbt "run --input-path <path-to-your-file>"`  
for e.g.  
`sbt "run --input-path E:\test.csv`

## Running test
`sbt test`