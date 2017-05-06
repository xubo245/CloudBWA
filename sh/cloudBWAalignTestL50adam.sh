
#for j in 10000 100000 1000000 10000000
for i in 50 100
do
#for i in 50 100
for j in 10000 100000 1000000 10000000
#for j in  10000000
do
#echo $i
#echo $j
#for time in 1 100 1000 5000 10000 100000 1000000 10000000
for time in 5000
do
for k in {1..5}
do
out='/xubo/project/alignment/sparkBWA/input/g38/cloudBWA/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12adam.sam'
file1='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired.adam'
#file2='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
file2="null"
sh testcloudBWAcluster.sh $file1 $file2 $out $time 32
done
done
done 
done
