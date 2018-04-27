# Java with R


# Requires

* Java 8+
* rJava

```
apt install r-cran-rjava
```


As root

```
R CMD javareconf
```

Install

```
install.packages("rJava")
```

Loaded

```
require(rJava)
Loading required package: rJava
```

Env Variables

```
export R_HOME=/usr/lib/R
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib/R/site-library/rJava/jri
```


Test

```
mvn test
```




# References

[R and Java](https://www.slideshare.net/rcuprak/r-and-javav12)

[Calling R from java using JRI](http://www.cnblogs.com/mavlarn/archive/2012/12/24/2831688.html)

[Graphs in R](https://www.harding.edu/fmccown/r/)