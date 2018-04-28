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


```
print(v)
[1] "Java-Object{Hello World!}"
```

Getting tmpDir from Java
```
tmpDir <- .jcall("java/lang/System","S","getProperty","java.io.tmpdir")
```

```
print(tmpDir)
[1] "/tmp"
```



# RStudio

Ubuntu 18.04 tested

```
# Removing corrupted install

sudo apt-get purge rstudio # If it is not installed there is no problem

# Getting the files needed

wget https://download1.rstudio.org/rstudio-1.1.447-amd64.deb
wget http://ubuntu.mirrors.tds.net/ubuntu/pool/universe/g/gstreamer0.10/libgstreamer0.10-0_0.10.36-1.5ubuntu1_amd64.deb
wget http://security.ubuntu.com/ubuntu/pool/main/g/gst-plugins-base0.10/libgstreamer-plugins-base0.10-0_0.10.36-1ubuntu0.2_amd64.deb

# Installing deppendencies

sudo apt-get install r-base r-base-dev
sudo dpkg -i libgstreamer0.10-0_0.10.36-1.5ubuntu1_amd64.deb
sudo dpkg -i libgstreamer-plugins-base0.10-0_0.10.36-1ubuntu0.2_amd64.deb
sudo dpkg -i rstudio-1.1.447-amd64.deb
```


# Database

H2

```
install.packages("RH2")
```

```
library(RH2)
con <- dbConnect(H2())
s <- 'create table tt("id" int primary key, "name" varchar(255))'
dbSendUpdate(con, s)
dbSendUpdate(con, "insert into tt values(1, 'Hello')")
dbSendUpdate(con, "insert into tt values(2, 'World')")
dbGetQuery(con, "select * from tt")
```

```
  id  name
1  1 Hello
2  2 World
```



# References

[R in Java](http://www.oracle.com/technetwork/java/jvmls2013vitek-2013524.pdf)

[jcall](https://www.rforge.net/doc/packages/rJava/jcall.html)

[RStudio Install](https://linuxconfig.org/install-r-on-ubuntu-18-04-bionic-beaver-linux)

[RH2](https://cran.r-project.org/web/packages/RH2/index.html)

