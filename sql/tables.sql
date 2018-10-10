
CREATE TABLE TransportationType (
                TransportationTypeId INT AUTO_INCREMENT NOT NULL,
                TransportationTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (TransportationTypeId)
);


CREATE TABLE DiningType (
                DiningTypeId INT AUTO_INCREMENT NOT NULL,
                DiningTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (DiningTypeId)
);


CREATE TABLE ActivityType (
                ActivityTypeId INT AUTO_INCREMENT NOT NULL,
                ActivityTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (ActivityTypeId)
);


CREATE TABLE Dining (
                DiningId INT AUTO_INCREMENT NOT NULL,
                DiningName VARCHAR(50) NOT NULL,
                DiningInformation VARCHAR(1000),
                PRIMARY KEY (DiningId)
);


CREATE TABLE Activity (
                ActivityId INT AUTO_INCREMENT NOT NULL,
                ActivityName VARCHAR(50) NOT NULL,
                ActivityInformation VARCHAR(1000),
                PRIMARY KEY (ActivityId)
);


CREATE TABLE AccommodationType (
                AccommodationTypeId INT AUTO_INCREMENT NOT NULL,
                AccommodationTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (AccommodationTypeId)
);


CREATE TABLE Accommodation (
                AccommodationId INT AUTO_INCREMENT NOT NULL,
                AccommodationName VARCHAR(50) NOT NULL,
                AccommodationInformation VARCHAR(1000),
                PRIMARY KEY (AccommodationId)
);


CREATE TABLE Transportation (
                TransportationId INT AUTO_INCREMENT NOT NULL,
                TransportationName VARCHAR(50) NOT NULL,
                TransportationInformation VARCHAR(1000),
                PRIMARY KEY (TransportationId)
);


CREATE TABLE Destination (
                DestinationId INT AUTO_INCREMENT NOT NULL,
                DestinationName VARCHAR(100) NOT NULL,
                DestinationInformation VARCHAR(2000),
                PRIMARY KEY (DestinationId)
);


CREATE TABLE User (
                UserId INT AUTO_INCREMENT NOT NULL,
                FirstName VARCHAR(75) NOT NULL,
                LastName VARCHAR(75) NOT NULL,
                ZipCode INT NOT NULL,
                EmailAddress VARCHAR(50) NOT NULL,
                BirthDate DATE NOT NULL,
                Password VARCHAR(50) NOT NULL,
                Restriction VARCHAR(500),
                Photo LONGBLOB,
                PRIMARY KEY (UserId)
);


CREATE TABLE ReqTrip (
                ReqTripId INT AUTO_INCREMENT NOT NULL,
                UserId INT NOT NULL,
                StartDate DATE NOT NULL,
                EndDate DATE NOT NULL,
                NumberOfTravelers INT NOT NULL,
                Pets BOOLEAN NOT NULL,
                Budget DECIMAL(10) NOT NULL,
                TripPurpose VARCHAR(200) NOT NULL,
                Notes VARCHAR(1000),
                NonNegotiable VARCHAR(500),
                ConsultantNotes VARCHAR(4000),
                PRIMARY KEY (ReqTripId)
);


CREATE TABLE TripDestination (
                TripDestinationId INT NOT NULL,
                DestinationId INT NOT NULL,
                ReqTripId INT NOT NULL,
                PRIMARY KEY (TripDestinationId)
);


CREATE TABLE TripActivity (
                TripActivityId INT AUTO_INCREMENT NOT NULL,
                ActivityId INT NOT NULL,
                ReqTripId INT NOT NULL,
                PRIMARY KEY (TripActivityId)
);


CREATE TABLE TripTransportation (
                TripTransportationId INT AUTO_INCREMENT NOT NULL,
                TransportationId INT NOT NULL,
                ReqTripId INT NOT NULL,
                PRIMARY KEY (TripTransportationId)
);


CREATE TABLE TripAccommodation (
                TripAccommodationId INT AUTO_INCREMENT NOT NULL,
                AccommodationId INT NOT NULL,
                ReqTripId INT NOT NULL,
                PRIMARY KEY (TripAccommodationId)
);


CREATE TABLE TripDining (
                TripDiningId INT AUTO_INCREMENT NOT NULL,
                DiningId INT NOT NULL,
                ReqTripId INT NOT NULL,
                PRIMARY KEY (TripDiningId)
);


CREATE TABLE ReqTripDestination (
                ReqTripDestinationId INT AUTO_INCREMENT NOT NULL,
                ReqTripId INT NOT NULL,
                DestinationId INT NOT NULL,
                PRIMARY KEY (ReqTripDestinationId)
);


CREATE TABLE ReqTripTransportation (
                ReqTripTransportationId INT AUTO_INCREMENT NOT NULL,
                ReqTripId INT NOT NULL,
                TransportationTypeId INT NOT NULL,
                PRIMARY KEY (ReqTripTransportationId)
);


CREATE TABLE ReqTripActivity (
                ReqTripActivityId INT AUTO_INCREMENT NOT NULL,
                ReqTripId INT NOT NULL,
                ActivityTypeId INT NOT NULL,
                PRIMARY KEY (ReqTripActivityId)
);


CREATE TABLE ReqTripDining (
                ReqTripDiningId INT AUTO_INCREMENT NOT NULL,
                ReqTripId INT NOT NULL,
                DiningTypeId INT NOT NULL,
                PRIMARY KEY (ReqTripDiningId)
);


CREATE TABLE ReqTripAccommodation (
                ReqTripAccommodationId INT AUTO_INCREMENT NOT NULL,
                ReqTripId INT NOT NULL,
                AccommodationTypeId INT NOT NULL,
                PRIMARY KEY (ReqTripAccommodationId)
);


ALTER TABLE ReqTripTransportation ADD CONSTRAINT transportationtype_reqtriptransportation_fk
FOREIGN KEY (TransportationTypeId)
REFERENCES TransportationType (TransportationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripDining ADD CONSTRAINT diningtype_reqtripdining_fk
FOREIGN KEY (DiningTypeId)
REFERENCES DiningType (DiningTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripActivity ADD CONSTRAINT activitytype_reqtripactivity_fk
FOREIGN KEY (ActivityTypeId)
REFERENCES ActivityType (ActivityTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDining ADD CONSTRAINT dining_tripdining_fk
FOREIGN KEY (DiningId)
REFERENCES Dining (DiningId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripActivity ADD CONSTRAINT activities_useractivities_fk
FOREIGN KEY (ActivityId)
REFERENCES Activity (ActivityId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripAccommodation ADD CONSTRAINT accommodationtype_reqtripaccommodation_fk
FOREIGN KEY (AccommodationTypeId)
REFERENCES AccommodationType (AccommodationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripAccommodation ADD CONSTRAINT accommodation_tripaccommodation_fk
FOREIGN KEY (AccommodationId)
REFERENCES Accommodation (AccommodationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripTransportation ADD CONSTRAINT transportation_usertransportation_fk
FOREIGN KEY (TransportationId)
REFERENCES Transportation (TransportationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDestination ADD CONSTRAINT destinations_userdestinations_fk
FOREIGN KEY (DestinationId)
REFERENCES Destination (DestinationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripDestination ADD CONSTRAINT destination_reqtripdestination_fk
FOREIGN KEY (DestinationId)
REFERENCES Destination (DestinationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTrip ADD CONSTRAINT user_reqtrip_fk
FOREIGN KEY (UserId)
REFERENCES User (UserId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripAccommodation ADD CONSTRAINT reqtrip_reqtripaccommodation_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripDining ADD CONSTRAINT reqtrip_reqtripdining_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripActivity ADD CONSTRAINT reqtrip_reqtripactivity_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripTransportation ADD CONSTRAINT reqtrip_reqtriptransportation_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripDestination ADD CONSTRAINT reqtrip_reqtripdestination_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDining ADD CONSTRAINT reqtrip_tripdining_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripAccommodation ADD CONSTRAINT reqtrip_tripaccommodation_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripTransportation ADD CONSTRAINT reqtrip_triptransportation_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripActivity ADD CONSTRAINT reqtrip_tripactivity_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDestination ADD CONSTRAINT reqtrip_tripdestination_fk
FOREIGN KEY (ReqTripId)
REFERENCES ReqTrip (ReqTripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
