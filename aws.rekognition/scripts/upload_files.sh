search_dir=
output=

for file_name in "$search_dir"/*.png
do

	aws s3 cp "$file_name" s3://$output

done
