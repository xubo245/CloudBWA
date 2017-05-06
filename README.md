# CloudBWA
CloudBWA: a distributed read mapping algorithms in GCDSS

## 1 Building GCDSS

####1.1 Preparing: we should prepare before run GCDSS.

- Apache Spark  : https://github.com/apache/spark
- Alluxio: http://www.alluxio.org/
- HDFS: https://github.com/apache/hadoop 
- BWA: https://github.com/lh3/bwa
- jBWA: https://github.com/lindenb/jbwa
 
##2.build
	mvn -DskipTests clean package
##3.run

	spark-submit --class org.gcdss.alignment.CloudBWA \
	 --master spark://219.219.220.149:7077 \
	 --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
	  --jars /home/hadoop/cloud/adam/lib/adam_2.10-0.23.0-SNAPSHOT.jar \
	  --executor-memory 20G \
	CloudBWA.jar \
	$1 $2 $3 $4 $5 $6 $7

Parameter:

	$1:index
    $2:fastq1
	$3:fastq2
	$4:output
	$5:batch
	$6:numPartition
	$7:seqindex

For Example:

	for i in 50
	do
		for time in  1000
		do
			for j in  16000000
			do
				for num in  32
				do
					for k in {1..1}
					do
						out='/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12time'$time'num'$num'k'$k'.adam'
						~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
						file1='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1F18.fastq'
						file2='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2F18.fastq'
						localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex'
						sh testcloudBWAAdamclusterAdam-0.21.1-snapshot.sh $file1 $file2 $out $time $num $localseqindex
					done
					~/cloud/alluxio-1.3.0/bin/alluxio fs free $file1
					done
			done
		done
	done

testcloudBWAAdamclusterAdam-0.21.1-snapshot.sh:

	 spark-submit --class org.gcdss.alignment.CloudBWAAdam \
	 --master spark://MasterIP:7077 \
	 --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
	 --jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.1-SNAPSHOT.jar \
	  --executor-memory 20G \
	CloudBWA.jar \
	/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.fasta \
	$1 $2 $3 $4 $5 $6
	 
more shell example in [sh](./sh) file 

## Tutorial

the Tutorial or docs is being written.

## Help

If you have any questions or suggestions, please write it in the issue of this project or send an e-mail to me: xubo245@mail.ustc.edu.cn