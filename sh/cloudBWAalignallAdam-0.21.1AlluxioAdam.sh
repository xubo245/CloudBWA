
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in 1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 1000 10000 100000 1000000 10000000
#for time in  1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 5000 50000 500000 5000000 1000 10000 100000 1000000 10000000
for i in 50
#for time in  100
do
#for j in 10000 100000 1000000 10000000
#for i in 50 100
for time in  10 1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 1000 5000 10000 50000 100000
#for time in  60 70 80 90 100 500 1000 5000 10000 50000 100000 500000 11 12 13 14 15 16 17 18 19 20
#for time in 500000 10 11 12 13 14 15 16 17 18 19 20
#for time in 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 5000 50000 500000 5000000 1000 10000 100000 1000000 10000000
do
#for i in 50 100
for j in 10000000
#for j in  10000000
do
#echo $i
#echo $j
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in 5000
#do

for k in {1..3}
do
out='alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12time'$time'k'$k'.adam'
~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
file1='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1.fastq'
file2='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
#localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GCA_000001405.15_GRCh38_full_analysis_set.seqIndex'
#sh testcloudBWAcluster.sh $file1 $file2 $out $time 32 $localseqindex
localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex'
sh testcloudBWAAdamclusterAdam-0.21.1-snapshot.sh $file1 $file2 $out $time 32 $localseqindex
~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
done

done
done 
done
#hdfsindex='/xubo/project/alignment/ref/GRCH38chr1L3556522.fasta'
