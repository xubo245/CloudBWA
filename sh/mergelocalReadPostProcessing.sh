
for num in 2000000 10000000 20000000
do 
#num=10000000
fq='/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L50c'$num'Nhs20Paired12time10num16k1.sam'
out='cloudBWAnewg38L50c'$num'Nhs20Paired12time10num16k1.sam'
sh testmergelocal.sh $fq $out
done
