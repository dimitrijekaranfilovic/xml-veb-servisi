help='false'
start='false'
stop='false'

print_usage(){
    echo "You run this script like any other. Its first argument is either 'start' or 'stop'.";
    echo "After that, you specify services to start or stop."
    echo "They can be one of: 'imunizacija', 'sluzbenici' or 'email'."
}


option=$1 # opcija moze biti start ili stop
service_1=$2
service_2=$3
service_3=$4

total="$service_1$service_2$service_3"



if [[ "$option" == "start" ]];then

    if [[ "$total" == *"imunizacija"* ]]; then
        echo "starting imunizacija service...";
        sudo docker start imunizacija-xml-exist-database;
        sudo docker start imunizacija-rdf-database;
        echo "";
    fi

    if [[ "$total" == *"sluzbenici"* ]]; then
        echo "starting sluzbenici service...";
        sudo docker start sluzbenici-xml-exist-database;
        sudo docker start sluzbenici-rdf-database;
        echo "";
    fi

    if [[ "$total" == *"email"* ]]; then
        echo "starting email service...";
        sudo docker start rabbitmq-message-broker;
        sudo docker start email-service-api;
        echo "";
    fi

    if [[ "$total" == "" ]]; then
        echo "No service specified."
    fi



elif [[ "$option" == "stop" ]];then
    if [[ "$total" == *"imunizacija"* ]]; then
        echo "stopping imunizacija service...";
        sudo docker stop imunizacija-xml-exist-database;
        sudo docker stop imunizacija-rdf-database;
        echo "";
    fi

    if [[ "$total" == *"sluzbenici"* ]]; then
        echo "stopping sluzbenici service...";
        sudo docker stop sluzbenici-xml-exist-database;
        sudo docker stop sluzbenici-rdf-database;
        echo "";
    fi

    if [[ "$total" == *"email"* ]]; then
        echo "stopping email service...";
        sudo docker stop email-service-api;
        sudo docker stop rabbitmq-message-broker;
        echo "";
    fi

    if [[ "$total" == "" ]]; then
        echo "No service specified."
    fi
else
    echo "Option '$option' not recognized."
    print_usage
    exit 0
fi