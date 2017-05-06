#~/cloud/alluxio-1.3.0/bin/alluxio fs mkdir -p /xubo/project/SparkSW/input/
#for j in 1 2 3 4 5 6
#for j in 7
#do
file1='/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired1.fastq'
file2='/xubo/project/alignment/sparkBWA/input/g38/newg38L50c10000000Nhs20Paired2.fastq'
   ~/cloud/alluxio-1.3.0/bin/alluxio fs load $file1
   ~/cloud/alluxio-1.3.0/bin/alluxio fs load $file2

    #  ~/cloud/alluxio-1.3.0/bin/alluxio fs load /xubo/project/SparkSW/input/D7Line.fasta
#done
