# R in Java


# Requires

* Java 8+


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
export R_HOME=/usr/local/lib/R
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$R_HOME/site-library/rJava/jri
```



```
.jinit()
 v <- new (J("java.lang.String"), "Hello World!")
```



# References

[R and Java](https://www.slideshare.net/rcuprak/r-and-javav12)

[Calling R from java using JRI](http://www.cnblogs.com/mavlarn/archive/2012/12/24/2831688.html)