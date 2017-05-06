
#for j in 10000 100000 1000000 10000000
for i in 50 100
do
#for i in 50 100
for j in 10000 100000 1000000 10000000
do
#echo $i
#echo $j
for k in {1..3}
do
#fq='g38L'$i'c'$j'Nhs20Paired'$k'.fq'
#fq0='g38L'$i'c'$j'Nhs20Paired*.fastq'
#fq1='/xubo/alignment/sparkBWA/g38L'$i'c'$j'Nhs20Paired1.fastq'
#fq2='/xubo/alignment/sparkBWA/g38L'$i'c'$j'Nhs20Paired2.fastq'
#out='g38L'$i'c'$j'Nhs20Paired12.sam'
out='/xubo/project/alignment/sparkBWA/input/g38/cloudBWA/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12.sam'
file1='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1.fastq'
file2='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
#/xubo/project/alignment/sparkBWA/input/g38/g38L50c10000Nhs20Paired1.fastqs

sh testcloudBWAcluster.sh $file1 $file2 $out

done
done 
done
