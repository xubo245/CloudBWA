#sh testverifyLength.sh adam  
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num32k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num48k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num64k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num80k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num96k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num128k1.adam
#sh testverifyLength.sh adam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num160k1.adam
sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num16k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num32k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num48k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num64k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num80k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num96k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num128k1.sam
#sh testverifyLength.sh sam  alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10num160k1.sam

#sh testverifyLength.sh sam
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time6k1.adam
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10k1.sam
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time40k1.sam
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time10k1.adam
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c10000000Nhs20Paired12time40k1.adam
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c100000Nhs20Paired12time6k1.sam
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c100000Nhs20Paired12time7k1.sam
#sh testverifyLength.sh adam  /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c100000Nhs20Paired12time6k1.adam
#sh testverifyLength.sh adam  /xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c100000Nhs20Paired12time7k1.adam

#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c10000000Nhs20Paired12.samtime20170309161411823
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c10000000Nhs20Paired12.samtime20170309213914994
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c10000000Nhs20Paired12.samtime20170309215219398
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c1000000Nhs20Paired12.samtime20170309173928635
#sh testverifyLength.sh sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c100000Nhs20Paired12.samtime20170309215142369
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c100000Nhs20Paired12.samtime20170309213837837
#sh testverifyLength.sh adam /xubo/project/alignment/CloudBWA/g38/time/














#spark-submit --class org.gcdss.alignment.utils.VerifyLength \
#  --master spark://219.219.220.149:7077 \
# --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
#  --jars /home/hadoop/cloud/adam/lib/adam-core_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-apis_2.10-0.19.0.jar \
#  --executor-memory 20G \
#CloudBWA.jar \
#sam /xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime5000newg38L50c10000000Nhs20Paired12.samtime20170309161411823
#/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.fasta \
#$1 $2 $3 $4 $5
## --archives ./bwa.zip \
#--master spark://219.219.220.149:7077 \
#--master local \
#--total-executor-cores 20 --executor-cores 2 --executor-memory 20G \
