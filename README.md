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


```
.jinit()
 v <- new (J("java.lang.String"), "Hello World!")
```



# References

[R in Java](http://www.oracle.com/technetwork/java/jvmls2013vitek-2013524.pdf)