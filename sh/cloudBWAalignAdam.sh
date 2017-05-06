
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in  1 2 3  4 5 6 7 8 9 10 
#for time in 7 8 9 10
for time in 5000
#for time in  1 2 10 100
do
#for j in 10000 100000 1000000 10000000
for i in 50
do
#for i in 50 100
#for j in 10000 100000 1000000 10000000
#for j in  100000 10000000
for j in 100000 10000000
do
#echo $i
#echo $j
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in 5000
#do
for k in {1..1}
do
#out='/xubo/project/alignment/sparkBWA/input/g38/cloudBWA/time/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12time'$time'.sam'
out='/xubo/project/alignment/CloudBWA/g38/time/cloudBWAtime'$time'newg38L'$i'c'$j'Nhs20Paired12.sam'
file1='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1.fastq'
file2='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
#hdfsindex='/xubo/project/alignment/ref/GRCH38chr1L3556522.fasta'
#sh testcloudBWAcluster.sh $file1 $file2 $out $time 32 $hdfsindex
localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex'
sh testcloudBWAAdamcluster.sh $file1 $file2 $out $time 32 $localseqindex

done
done
done 
done
