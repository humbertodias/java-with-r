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



# References

[R in Java](http://www.oracle.com/technetwork/java/jvmls2013vitek-2013524.pdf)

[jcall](https://www.rforge.net/doc/packages/rJava/jcall.html)

[RStudio](https://www.rstudio.com/products/rstudio/download/#download)

[RStudio Install](https://linuxconfig.org/install-r-on-ubuntu-18-04-bionic-beaver-linux)

