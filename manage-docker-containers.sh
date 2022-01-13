# sudo docker start imunizacija-xml-exist-database
# sudo docker start imunizacija-rdf-database
# sudo docker start sluzbenici-xml-exist-database
# sudo docker start sluzbenici-rdf-database

#i - imunizacija
#s - sluzbenici
#e - email

help='false'

print_usage(){
    echo "You can run this script with either -s, -e or -h flag.";
}

while getopts s:e:h flag; do
    case "${flag}" in
        e) end=${OPTARG} ;;
        s) start=${OPTARG} ;;
        h) help='true' ;;
    esac
done

if (( $OPTIND == 1 )); then
   print_usage
   exit 0
fi


if "$help"; then
    echo "-s           Flag for starting services. Its value can be one of 'i'(imunizacija), 's'(sluzbenici), 'e'(email) or any combination of them. It starts all docker containers needed for these services.";
    echo "-e           Same as above, except it stops docker containers. e stands for end, because I can't have two -s flags now can I";
    echo "-h           Displays this."
    echo ""
    echo "Some examples:"
    echo "1. ./manage-docker-containers.sh -s i  => Starts containers for 'imunizacija' service."
    echo "2. ./manage-docker-containers.sh -e ise => Stops containers for 'imunizacija', 'sluzbenici' and 'email' services."
    exit 0
fi

if ([  -z "$end" ] && [ -z "$start" ]) || ([ ! -z "$end" ] && [ ! -z "$start" ]); then
    echo "There can only be either -s or -e argument present at the time.";
else
    if [ ! -z "$start" ]; then
        if [[ "$start" == *"i"* ]]; then
            echo "starting imunizacija service...";
            sudo docker start imunizacija-xml-exist-database;
            sudo docker start imunizacija-rdf-database;
            echo "";
        fi
        
        if [[ "$start" == *"s"* ]]; then
            echo "starting sluzbenici service...";
            sudo docker start sluzbenici-xml-exist-database;
            sudo docker start sluzbenici-rdf-database;
            echo "";
        fi

        if [[ "$start" == *"e"* ]]; then
            echo "starting email service...";
            sudo docker start rabbitmq-message-broker;
            sudo docker start email-service-api;
            echo "";
        fi
    
    else
        if [[ "$end" == *"i"* ]]; then
            echo "stopping imunizacija service...";
            sudo docker stop imunizacija-xml-exist-database;
            sudo docker stop imunizacija-rdf-database;
            echo "";
        fi
        
        if [[ "$end" == *"s"* ]]; then
            echo "stopping sluzbenici service...";
            sudo docker stop sluzbenici-xml-exist-database;
            sudo docker stop sluzbenici-rdf-database;
            echo "";
        fi

        if [[ "$end" == *"e"* ]]; then
            echo "stopping email service...";
            sudo docker stop email-service-api;
            sudo docker stop rabbitmq-message-broker;
            echo "";
        fi
    fi
fi