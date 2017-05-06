	git reset --hard FETCH_HEAD
	git pull hadoop223 master
	mvn package
	chmod 777 down.sh

