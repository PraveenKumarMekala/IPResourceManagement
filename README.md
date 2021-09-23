# IPResourceManagement

Assumptions:

Lower Bound always saved to 0

Upper Bound always saved to 250

All pools address for each pool id will created in between 0 to 250

Created EmbeddedId to avoid constraint violation



How to run and test the application

Run mvn clean install

Launch IPResourceManagement in either debug or run mode

open localhost:8080/swagger-ui.html

POST: Insert some data with description, pool id and number of addresses required

GET: use get end point on the same id used while posting data.