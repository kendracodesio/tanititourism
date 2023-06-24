INSERT INTO do_type (type_name) VALUES
('Explore By Sea'),
('Explore By Foot'),
('Explore By Air'),
('Bus Tours'),
('All Things Bike'),
('History'),
('Adventure'),
('Family Friendly'),
('Romance'),
('For A Rainy Day');


INSERT INTO regions (name) VALUES
('Downtown Taniti City'),
('Taniti City - East'),
('Taniti City - West'),
('Merriton Landing'),
('Yellow Leaf Bay'),
('Island Interior Region');

INSERT INTO stay_type (type_name) VALUES
('Hostels'),
('Hotels'),
('Bed And Breakfasts'),
('Resorts'),
('Homes & Condos');

INSERT INTO dine_type (type_name) VALUES
('Local Flavor Restaurants'),
('Pan-Asian Restaurants'),
('American Restaurants'),
('Grocery/Convenience Stores'),
('Pubs & Breweries'),
('Dance Clubs');


INSERT INTO users (username, password, first_name, last_name, role) VALUES ('demo', '$2a$10$mtwOY1XQTPL112HUZ3HfuulYtCQlANX9EURi/hfXGcrPs99ebpVxS', 'John', 'Doe', 'ADMIN');

INSERT INTO users (username, password, first_name, last_name, role) VALUES ('demo1', '$2a$10$sqoFg09sGidzaxw939Tk/.VhYfaBedd85piHIEk1dNzdtBvhXcu5.', 'Jane', 'Doe', 'ADMIN');

INSERT INTO users (username, password, first_name, last_name, role) VALUES ('demo2','$2a$10$K3Je2nHyEMjmibGq3xoVR.It0zLbiDwSFDzu5jn82R0Hyf/NWcw92', 'Mary', 'Smith', 'ADMIN');

INSERT INTO users (username, password, first_name, last_name, role) VALUES ('demo3','$2a$10$M3D7Rr8eD8vzzh3Fa/K7rOjRXag3OU27Dg.244fX41BX.bmSgnrbi', 'James', 'Johnson', 'ADMIN');