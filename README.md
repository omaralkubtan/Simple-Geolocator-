# Simple Geolocator Backend

The Simple Geolocator Backend is a robust system built with Spring Boot. It serves as the backbone of the application, handling geolocation data. The backend receives address inputs via the `/search` API, checks if the geolocation data for these addresses is available in the PostgreSQL database, and if not, fetches the data from a third-party API. The newly fetched data is then stored in the database for future use. The backend also has an email functionality implemented using Java Mail Sender, which allows it to send the geolocation results via the `/send` API.

## APIs

- **/search?query="querystringexample"**: This API receives address inputs as a query parameter.
- **/send**: This API sends the email and geolocation data via the request body.

## Backend 

- **API Endpoint**: Endpoint to handle requests.
- **Database Check**: Looking up the address in the database; if not found, use a third-party API ([Open Street Map](https://www.openstreetmap.org/)) to get geolocation data.
- **Email Functionality**: Implemented using Java Mail Sender to email the results.
- **Error Handling**: Basic error communication.
- **Use Spring Data JPA**

## Setup

The application uses a PostgreSQL database and the data configuration is set via environment variables. To run the project, you need to set the following environment variables:

- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`

## Deployment

The application is containerized using Docker and can be deployed using the Render hosting service. In Render, you can set the environment variables and run the application.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
