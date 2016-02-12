# StuRents API Helper


## Installation

Install using maven. Can be tested using `com.sturents.api.Cli` by running:

    java -cp /path/to/jar/file com.sturents.api.Cli LANDLORD_ID API_KEY

With the arguments in capitals replaced by their respective values. It will load a file in the document root called "data.json"
     
## Send data to StuRents

    import com.sturents.api.SendHouses;

    ...

    String response = SendHouses.run(landlord_id, api_key, json);
