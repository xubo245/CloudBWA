
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in 1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 1000 10000 100000 1000000 10000000
#for time in  1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 5000 50000 500000 5000000 1000 10000 100000 1000000 10000000
for i in 100
#for time in  100
do
#for j in 10000 100000 1000000 10000000
#for i in 50 100
#for time in  10 1 2 3  4 5 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 5000 50000 500000 5000000 1000 10000 100000 1000000 10000000
#for time in  60 70 80 90 100 500 1000 5000 10000 50000 100000 500000 11 12 13 14 15 16 17 18 19 20
#for time in 10 100 1000
for time in  10
#for time in 6 7 8 9 10 20 30 40 50 60 70 80 90 100 500 5000 50000 500000 5000000 1000 10000 100000 1000000 10000000
do
#for i in 50 100
#for j in {2000000..20000000..2000000}
#for j in {14000000..20000000..2000000}
for j in  5000000
do
#for time in 5000 100 1000 10000 100000 1000000 10000000 1 2 3  4 5 6 7 8 9 10
#for time in 5000
#do
#for num in 16 22 24 28 29 30 31 32 40 46 48  56 60 62 64 72 80 88 92 94 96 104 112 120 124 126 128 136 144 152 156 158 160 188 190 192 224 256 288 320 352 384 416 448 480 512 544 576 608 640 672 8
#for num in 16
#do
for core in {1..9..1}
do
let cores=10-$core
#let num=cores*2
num=16
#for num in 16
#do
for k in {1..3}
do
out='alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12time'$time'num'$num'k'$k'.sam'
~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
file1='/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired.adam'
#file1='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1.fastq'
#file2='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
#localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GCA_000001405.15_GRCh38_full_analysis_set.seqIndex'
#sh testcloudBWAcluster.sh $file1 $file2 $out $time 32 $localseqindex
localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex'
#sh testcloudBWAclusterAdam-0.21.1-snapshot10G.sh $file1 $file2 $out $time $num $localseqindex
#sh testcloudBWAclusterAdam-0.21.1-snapshot10G.sh $file1 "null" $out $time $num $localseqindex
sh testcloudBWAclusterAdam-0.21.1-snapshotscalability.sh $file1 "null" $out $time $num $localseqindex $cores

~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
done
done
#for k in {1..5}
#do
#out='alluxio://Master:19998/xubo/project/alignment/CloudBWA/g38/time/cloudBWAnewg38L'$i'c'$j'Nhs20Paired12time'$time'num'$num'k'$k'.adam'
#~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
#file1='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired.adam'
#file1='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired1.fastq'
#file2='alluxio://Master:19998/xubo/project/alignment/sparkBWA/input/g38/newg38L'$i'c'$j'Nhs20Paired2.fastq'
#localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GCA_000001405.15_GRCh38_full_analysis_set.seqIndex'
#sh testcloudBWAcluster.sh $file1 $file2 $out $time 32 $localseqindex
#localseqindex='/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.seqIndex'
#sh testcloudBWAAdamclusterAdam-0.21.1-snapshot10G.sh $file1 $file2 $out $time $num $localseqindex
#sh testcloudBWAAdamclusterAdam-0.21.1-snapshot10G.sh $file1 "null" $out $time $num $localseqindex
#sh testcloudBWAAdamclusterAdam-0.21.1-snapshot.sh $file1 "null" $out $time $num $localseqindex
#~/cloud/alluxio-1.3.0/bin/alluxio fs rm -R $out
#done
#~/cloud/alluxio-1.3.0/bin/alluxio fs free $file1 
done
done
done 
#done
#hdfsindex='/xubo/project/alignment/ref/GRCH38chr1L3556522.fasta'
