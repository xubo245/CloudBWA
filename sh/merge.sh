spark-submit --class org.gcdss.alignment.Merge \
  --master spark://219.219.220.149:7077 \
 --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
 --jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.1-SNAPSHOT.jar \
  --executor-memory 20G \
CloudBWA.jar \
/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.sam \
/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.merge.sam
#/xubo/project/alignment/sparkBWA/input/g38/cloudBWA/time/cloudBWAnewg38L50c10000Nhs20Paired12time100.sam20170307105838872 /xubo/project/alignment/sparkBWA/input/g38/cloudBWA/time/cloudBWAnewg38L50c10000Nhs20Paired12time100.sam20170307105838872Par1
 #--jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.0.jar \
## --archives ./bwa.zip \
#--master spark://219.219.220.149:7077 \
#--master local \
#--total-executor-cores 20 --executor-cores 2 --executor-memory 20G \
#--jars /home/hadoop/cloud/adam/lib/adam-apis_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-core_2.10-0.18.3-SNAPSHOT.jar \
# --jars /home/hadoop/cloud/adam/lib/adam-apis-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-cli-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-core-spark2_2.10-0.21.0.jar \
#--jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-apis-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-cli-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-core-spark2_2.10-0.21.0.jar \
