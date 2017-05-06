spark-submit --class org.gcdss.alignment.utils.VerifyLength \
  --master spark://219.219.220.149:7077 \
 --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
 --jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.1-SNAPSHOT.jar \
 --executor-memory 20G \
  CloudBWA.jar \
  $1 $2
 #--jars /home/hadoop/cloud/adam/lib/adam-core_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-apis_2.10-0.19.0.jar \
#sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c10000000Nhs20Paired12.samtime20170309161411823
#/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.fasta \
#$1 $2 $3 $4 $5
## --archives ./bwa.zip \
#--master spark://219.219.220.149:7077 \
#--master local \
#--total-executor-cores 20 --executor-cores 2 --executor-memory 20G \
