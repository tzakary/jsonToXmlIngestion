# jsonToXmlIngestion
Why was MediaPointsIngestionJSONtoXML.java class required?
1. The current method being used for ingesting media points in the body of API requests can only handle one pair of media points.
2. There exist uses cases where an API request can have multiple pairs of media points and the current ingestion method isn't equipped to handle the said use case.
3. The current method uses an XML template for ingesting the media points, which further hampers it from becoming dynamic.

What does the MediaPointsIngestionJSONtoXML.java class do?
1. MediaPointsIngestionJSONtoXML.java class works on a JSON object which has got all the media points required for the body of a said API request.
2. It creates an XML document on the fly, so that it isn't restricted to any given template. This also allows it ingest varying number of media point pairs.
3. The JSON is parsed and the fresh XML document created is updated according to the contents of the JSON object.
4. It can handle multiple number of media point pairs, the <Apply> and <Remove> tags, and any number of <ZIP> tags.

Things to keep in mind while going through MediaPointsIngestionJSONtoXML.java class:
1. Although functional, this is a barebone method which solely focuses on handling varying number of media points and ZIP tags.
2. For the sake of simplicity adding attributes to the tags of the XML has been skipped as the main purpose of this method is to handle dynamic number of media points. However, the method can be very easily tweaked to add the attributes.
3. The entire method is in one class, segregation of code hasn't been performed.
4. A screenshot of the final XML document created is added to this repo for the reader's consideration.
 
