# Java with R


# Requires

* Java 8+
* R package rJava
* R packages for test: sqldf, RH2

```
apt install r-cran-rjava
```

As root

```
R CMD javareconf
```

Install

```
R
install.packages("rJava")
```

Loaded

```
require(rJava)
```

Finally, should return

```
Loading required package: rJava
```

Exiting

```
q()

or

CTRL+R
```

Env Variables

```
export R_HOME=/usr/lib/R
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$R_HOME/site-library/rJava/jri
```


Test


R Packages

```
R
install.packages('sqldf')
install.packages('RH2')
```

JUnit

```
mvn test
```

# References

[R and Java](https://www.slideshare.net/rcuprak/r-and-javav12)

[Calling R from java using JRI](http://www.cnblogs.com/mavlarn/archive/2012/12/24/2831688.html)

[Graphs in R](https://www.harding.edu/fmccown/r/)