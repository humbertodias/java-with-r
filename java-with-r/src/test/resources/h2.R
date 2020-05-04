library(RH2)
con <- dbConnect(H2(),"jdbc:h2:mem:")
s <- 'create table tt("id" int primary key, "name" varchar(255))'
dbSendUpdate(con, s)
dbSendUpdate(con, "insert into tt values(1, 'Hello')")
dbSendUpdate(con, "insert into tt values(2, 'World')")
rows <- dbGetQuery(con, "select * from tt")
dbDisconnect(con)            # Close connection
print(rows[2])