
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


CREATE TABLE NonNegotiable (
                NonNegotiableId INT AUTO_INCREMENT NOT NULL,
                NonNegotiableName VARCHAR(50) NOT NULL,
                PRIMARY KEY (NonNegotiableId)
);


CREATE TABLE ActivityType (
                ActivityTypeId INT AUTO_INCREMENT NOT NULL,
                ActivityTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (ActivityTypeId)
);


CREATE TABLE Restriction (
                RestrictionId INT AUTO_INCREMENT NOT NULL,
                RestrictionName VARCHAR(50) NOT NULL,
                PRIMARY KEY (RestrictionId)
);


CREATE TABLE AccommodationType (
                AccommodationTypeId INT AUTO_INCREMENT NOT NULL,
                AccommodationTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (AccommodationTypeId)
);


CREATE TABLE Transportation (
                TransportationId INT AUTO_INCREMENT NOT NULL,
                TransportationName VARCHAR(50) NOT NULL,
                TransportationTypeId INT NOT NULL,
                TransportationDescription VARCHAR(2000),
                PRIMARY KEY (TransportationId)
);


CREATE TABLE Destination (
                DestinationId INT AUTO_INCREMENT NOT NULL,
                City VARCHAR(50) NOT NULL,
                Country VARCHAR(50) NOT NULL,
                DestinationDescription VARCHAR(2000),
                PRIMARY KEY (DestinationId)
);


CREATE TABLE Accommodation (
                AccommodationId INT AUTO_INCREMENT NOT NULL,
                AccommodationName VARCHAR(50) NOT NULL,
                AccommodationTypeId INT NOT NULL,
                DestinationId INT NOT NULL,
                AccommodationDescription VARCHAR(2000),
                PRIMARY KEY (AccommodationId)
);


CREATE TABLE Dining (
                DiningId INT AUTO_INCREMENT NOT NULL,
                DiningName VARCHAR(50) NOT NULL,
                DestinationId INT NOT NULL,
                DiningTypeId INT NOT NULL,
                DiningDescription VARCHAR(2000),
                PRIMARY KEY (DiningId)
);


CREATE TABLE Activity (
                ActivityId INT AUTO_INCREMENT NOT NULL,
                ActivityName VARCHAR(50) NOT NULL,
                DestinationId INT NOT NULL,
                ActivityTypeId INT NOT NULL,
                ActivityDescription VARCHAR(2000),
                PRIMARY KEY (ActivityId)
);


CREATE TABLE User (
                UserId INT AUTO_INCREMENT NOT NULL,
                UserName VARCHAR(75) NOT NULL,
                ZipCode INT NOT NULL,
                EmailAddress VARCHAR(50) NOT NULL,
                BirthDate DATE NOT NULL,
                Password VARCHAR(50) NOT NULL,
                PRIMARY KEY (UserId)
);


CREATE TABLE ReqTrip (
                ReqTripId INT AUTO_INCREMENT NOT NULL,
                UserId INT NOT NULL,
                StartDate DATE NOT NULL,
                EndDate DATE NOT NULL,
                NumberOfTravelers INT NOT NULL,
                Pets VARCHAR(50) NOT NULL,
                Budget DECIMAL(2) NOT NULL,
                TripPurpose VARCHAR(200) NOT NULL,
                PRIMARY KEY (ReqTripId)
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


CREATE TABLE Trip (
                TripId INT AUTO_INCREMENT NOT NULL,
                UserId INT NOT NULL,
                StartDate DATE NOT NULL,
                EndDate DATE NOT NULL,
                NumberOfTravelers INT NOT NULL,
                Pets VARCHAR(50) NOT NULL,
                Budget DECIMAL(2) NOT NULL,
                TripDescription VARCHAR(4000) NOT NULL,
                PRIMARY KEY (TripId)
);


CREATE TABLE TripNonNegotiable (
                TripNonNegotiableId INT AUTO_INCREMENT NOT NULL,
                NonNegotiableId INT NOT NULL,
                TripId INT NOT NULL,
                PRIMARY KEY (TripNonNegotiableId)
);


CREATE TABLE TripDining (
                TripDiningId INT AUTO_INCREMENT NOT NULL,
                DiningId INT NOT NULL,
                TripId INT NOT NULL,
                PRIMARY KEY (TripDiningId)
);


CREATE TABLE TripAccommodation (
                TripAccommodationId INT AUTO_INCREMENT NOT NULL,
                AccommodationId INT NOT NULL,
                TripId INT NOT NULL,
                StartDate DATE NOT NULL,
                EndDate DATE NOT NULL,
                PRIMARY KEY (TripAccommodationId)
);


CREATE TABLE TripDestination (
                TripDestinationId INT NOT NULL,
                DestinationId INT NOT NULL,
                TripId INT NOT NULL,
                PRIMARY KEY (TripDestinationId)
);


CREATE TABLE TripTransportation (
                TripTransportationId INT AUTO_INCREMENT NOT NULL,
                TransportationId INT NOT NULL,
                TripId INT NOT NULL,
                StartDate DATE NOT NULL,
                EndDate DATE NOT NULL,
                PRIMARY KEY (TripTransportationId)
);


CREATE TABLE TripActivity (
                TripActivityId INT AUTO_INCREMENT NOT NULL,
                ActivityId INT NOT NULL,
                TripId INT NOT NULL,
                PRIMARY KEY (TripActivityId)
);


CREATE TABLE UserRestriction (
                UserRestrictionId INT AUTO_INCREMENT NOT NULL,
                UserId INT NOT NULL,
                RestrictionId INT NOT NULL,
                PRIMARY KEY (UserRestrictionId)
);


ALTER TABLE Transportation ADD CONSTRAINT transportationtype_transportation_fk
FOREIGN KEY (TransportationTypeId)
REFERENCES TransportationType (TransportationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripTransportation ADD CONSTRAINT transportationtype_reqtriptransportation_fk
FOREIGN KEY (TransportationTypeId)
REFERENCES TransportationType (TransportationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Dining ADD CONSTRAINT diningoptiontype_diningoption_fk
FOREIGN KEY (DiningTypeId)
REFERENCES DiningType (DiningTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripDining ADD CONSTRAINT diningtype_reqtripdining_fk
FOREIGN KEY (DiningTypeId)
REFERENCES DiningType (DiningTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripNonNegotiable ADD CONSTRAINT nonnegotiable_usernonnegotiable_fk
FOREIGN KEY (NonNegotiableId)
REFERENCES NonNegotiable (NonNegotiableId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Activity ADD CONSTRAINT activitytype_activities_fk
FOREIGN KEY (ActivityTypeId)
REFERENCES ActivityType (ActivityTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripActivity ADD CONSTRAINT activitytype_reqtripactivity_fk
FOREIGN KEY (ActivityTypeId)
REFERENCES ActivityType (ActivityTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE UserRestriction ADD CONSTRAINT restrictions_userrestrictions_fk
FOREIGN KEY (RestrictionId)
REFERENCES Restriction (RestrictionId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Accommodation ADD CONSTRAINT accomodationtype_accommodations_fk
FOREIGN KEY (AccommodationTypeId)
REFERENCES AccommodationType (AccommodationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE ReqTripAccommodation ADD CONSTRAINT accommodationtype_reqtripaccommodation_fk
FOREIGN KEY (AccommodationTypeId)
REFERENCES AccommodationType (AccommodationTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripTransportation ADD CONSTRAINT transportation_usertransportation_fk
FOREIGN KEY (TransportationId)
REFERENCES Transportation (TransportationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Activity ADD CONSTRAINT destinations_activities_fk
FOREIGN KEY (DestinationId)
REFERENCES Destination (DestinationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Dining ADD CONSTRAINT destinations_diningoptions_fk
FOREIGN KEY (DestinationId)
REFERENCES Destination (DestinationId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Accommodation ADD CONSTRAINT destinations_accommodations_fk
FOREIGN KEY (DestinationId)
REFERENCES Destination (DestinationId)
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

ALTER TABLE TripAccommodation ADD CONSTRAINT accommodation_tripaccommodation_fk
FOREIGN KEY (AccommodationId)
REFERENCES Accommodation (AccommodationId)
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

ALTER TABLE UserRestriction ADD CONSTRAINT users_userrestrictions_fk
FOREIGN KEY (UserId)
REFERENCES User (UserId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Trip ADD CONSTRAINT user_trip_fk
FOREIGN KEY (UserId)
REFERENCES User (UserId)
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

ALTER TABLE TripActivity ADD CONSTRAINT trip_useractivity_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripTransportation ADD CONSTRAINT trip_usertransportation_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDestination ADD CONSTRAINT trip_userdestination_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripAccommodation ADD CONSTRAINT trip_tripaccommodation_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripDining ADD CONSTRAINT trip_tripdining_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE TripNonNegotiable ADD CONSTRAINT trip_tripnonnegotiable_fk
FOREIGN KEY (TripId)
REFERENCES Trip (TripId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
