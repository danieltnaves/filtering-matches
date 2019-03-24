db.createUser(
    {
        user: "appuser",
        pwd: "123",
        roles: [
            {
                role: "readWrite",
                db: "filtering-service"
            }
        ]
    }
);

try {
    db.matches.insertMany(


        [
            {
                "age": 41,
                "cityName": "Leeds",
                "compatibilityScore": 0.76,
                "contactsExchanged": 2,
                "displayName": "Caroline",
                "favourite": true,
                "heightInCm": 153,
                "jobTitle": "Corporate Lawyer",
                "location": {
                    "coordinates": [
                        -1.548567,
                        53.801277
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 47,
                "cityName": "Solihull",
                "compatibilityScore": 0.97,
                "contactsExchanged": 0,
                "displayName": "Sharon",
                "favourite": false,
                "heightInCm": 161,
                "jobTitle": "Doctor",
                "location": {
                    "coordinates": [
                        -1.778197,
                        52.412811
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Islam"
            },
            {
                "age": 38,
                "cityName": "Cardiff",
                "compatibilityScore": 0.47,
                "contactsExchanged": 5,
                "displayName": "Natalia",
                "favourite": false,
                "heightInCm": 144,
                "jobTitle": "Project Manager",
                "location": {
                    "coordinates": [
                        -3.17909,
                        51.481583
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 55,
                "cityName": "Eastbourne",
                "compatibilityScore": 0.95,
                "contactsExchanged": 0,
                "displayName": "Marta",
                "favourite": false,
                "heightInCm": 140,
                "jobTitle": "Finance",
                "location": {
                    "coordinates": [
                        0.290472,
                        50.768036
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Agnostic"
            },
            {
                "age": 43,
                "cityName": "London",
                "compatibilityScore": 0.88,
                "contactsExchanged": 0,
                "displayName": "Maria",
                "favourite": false,
                "heightInCm": 175,
                "jobTitle": "CEO",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 39,
                "cityName": "London",
                "compatibilityScore": 0.87,
                "contactsExchanged": 4,
                "displayName": "Stephanie",
                "favourite": false,
                "heightInCm": 153,
                "jobTitle": "Project Manager",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "religion": "Christian"
            },
            {
                "age": 48,
                "cityName": "London",
                "compatibilityScore": 0.83,
                "contactsExchanged": 6,
                "displayName": "Claire",
                "favourite": false,
                "heightInCm": 167,
                "jobTitle": "GP",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 39,
                "cityName": "Swindon",
                "compatibilityScore": 0.89,
                "contactsExchanged": 2,
                "displayName": "Colette",
                "favourite": false,
                "heightInCm": 177,
                "jobTitle": "Doctor - Hospital",
                "location": {
                    "coordinates": [
                        -1.772232,
                        51.568535
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 43,
                "cityName": "Oxford",
                "compatibilityScore": 0.91,
                "contactsExchanged": 1,
                "displayName": "Caroline",
                "favourite": false,
                "heightInCm": 160,
                "jobTitle": "Marketing Consultant",
                "location": {
                    "coordinates": [
                        -1.257677,
                        51.752022
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 42,
                "cityName": "Salisbury",
                "compatibilityScore": 0.97,
                "contactsExchanged": 10,
                "displayName": "Kate",
                "favourite": false,
                "heightInCm": 160,
                "jobTitle": "Psychologist",
                "location": {
                    "coordinates": [
                        -1.794472,
                        51.068787
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Buddhist"
            },
            {
                "age": 40,
                "cityName": "Weymouth",
                "compatibilityScore": 0.94,
                "contactsExchanged": 0,
                "displayName": "Katie",
                "favourite": false,
                "heightInCm": 148,
                "jobTitle": "Lawyer",
                "location": {
                    "coordinates": [
                        -2.457621,
                        50.614429
                    ],
                    "type": "Point"
                },
                "religion": "Atheist"
            },
            {
                "age": 40,
                "cityName": "Bournemouth",
                "compatibilityScore": 0.9,
                "contactsExchanged": 8,
                "displayName": "Clare",
                "favourite": false,
                "heightInCm": 144,
                "jobTitle": "Accountant",
                "location": {
                    "coordinates": [
                        -1.904755,
                        50.720806
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 39,
                "cityName": "Plymouth",
                "compatibilityScore": 0.89,
                "contactsExchanged": 0,
                "displayName": "Laura",
                "favourite": false,
                "heightInCm": 160,
                "jobTitle": "Lawyer",
                "location": {
                    "coordinates": [
                        -4.143841,
                        50.376289
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 39,
                "cityName": "Inverness",
                "compatibilityScore": 0.87,
                "contactsExchanged": 0,
                "displayName": "Katlin",
                "favourite": true,
                "heightInCm": 153,
                "jobTitle": "Barrister",
                "location": {
                    "coordinates": [
                        -4.224721,
                        57.477772
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Jewish"
            },
            {
                "age": 39,
                "cityName": "Aberdeen",
                "compatibilityScore": 0.87,
                "contactsExchanged": 0,
                "displayName": "Tracy",
                "favourite": false,
                "heightInCm": 153,
                "jobTitle": "Lawyer",
                "location": {
                    "coordinates": [
                        -2.099075,
                        57.149651
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 50,
                "cityName": "Ayr",
                "compatibilityScore": 0.93,
                "contactsExchanged": 8,
                "displayName": "Angie",
                "favourite": true,
                "heightInCm": 153,
                "jobTitle": "Accountant",
                "location": {
                    "coordinates": [
                        -4.629179,
                        55.458565
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 32,
                "cityName": "Belfast",
                "compatibilityScore": 0.89,
                "contactsExchanged": 0,
                "displayName": "Samantha",
                "favourite": false,
                "heightInCm": 161,
                "jobTitle": "Project Manager",
                "location": {
                    "coordinates": [
                        -5.926437,
                        54.607868
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 41,
                "cityName": "Londonderry",
                "compatibilityScore": 0.83,
                "contactsExchanged": 4,
                "displayName": "Elizabeth",
                "favourite": true,
                "heightInCm": 145,
                "jobTitle": "Dentist",
                "location": {
                    "coordinates": [
                        -7.318268,
                        55.006763
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Islam"
            },
            {
                "age": 40,
                "cityName": "Leeds",
                "compatibilityScore": 0.73,
                "contactsExchanged": 0,
                "displayName": "Emma",
                "favourite": false,
                "heightInCm": 150,
                "jobTitle": "Banker",
                "location": {
                    "coordinates": [
                        -1.548567,
                        53.801277
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 44,
                "cityName": "London",
                "compatibilityScore": 0.5,
                "contactsExchanged": 0,
                "displayName": "Diana",
                "favourite": false,
                "heightInCm": 153,
                "jobTitle": "Consultant",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 45,
                "cityName": "London",
                "compatibilityScore": 0.88,
                "contactsExchanged": 10,
                "displayName": "Kysha",
                "favourite": true,
                "heightInCm": 144,
                "jobTitle": "Lawyer",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "religion": "Islam"
            },
            {
                "age": 38,
                "cityName": "Swindon",
                "compatibilityScore": 0.88,
                "contactsExchanged": 0,
                "displayName": "Anne",
                "favourite": false,
                "heightInCm": 170,
                "jobTitle": "Marketing Consultant",
                "location": {
                    "coordinates": [
                        -1.772232,
                        51.568535
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Jewish"
            },
            {
                "age": 37,
                "cityName": "Bournemouth",
                "compatibilityScore": 0.76,
                "contactsExchanged": 0,
                "displayName": "Daniela",
                "favourite": false,
                "heightInCm": 177,
                "jobTitle": "Doctor",
                "location": {
                    "coordinates": [
                        -1.904755,
                        50.720806
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            },
            {
                "age": 39,
                "cityName": "London",
                "compatibilityScore": 0.99,
                "contactsExchanged": 50,
                "displayName": "Katherine",
                "favourite": true,
                "heightInCm": 177,
                "jobTitle": "Lawyer",
                "location": {
                    "coordinates": [
                        -0.118092,
                        51.509865
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Atheist"
            },
            {
                "age": 25,
                "cityName": "Harlow",
                "compatibilityScore": 0.88,
                "contactsExchanged": 0,
                "displayName": "Susan",
                "favourite": false,
                "heightInCm": 166,
                "jobTitle": "Project Manager",
                "location": {
                    "coordinates": [
                        0.10231,
                        51.772938
                    ],
                    "type": "Point"
                },
                "mainPhoto": "http://thecatapi.com/api/images/get?format=src&type=gif",
                "religion": "Christian"
            }
        ]


     );
 } catch (e) {
    print (e);
 }
