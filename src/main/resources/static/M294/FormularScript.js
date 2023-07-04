//Post
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("animalForm").addEventListener("submit", addAnimal);
});

function addAnimal(event) {
    event.preventDefault();

    var animalName = document.getElementById("animalName").value;
    var animalType = document.getElementById("animalType").value;
    var animalAge = document.getElementById("animalAge").value;
    var animalTypeInput = document.getElementById("animalType");
    var animalNameInput = document.getElementById("animalName");
    var animalAgeInput = document.getElementById("animalAge");

    // Validate input values
    if (animalAge < 1) {
        alert("Please enter a valid age.");
        animalAgeInput.value="";
        return;
    }
    if( animalType < 1 || animalType > 5 ){
        alert("There are only 1-5 animal types.");
        animalTypeInput.value="";
        return;
    }

    // Create the request body as a JSON object
    var requestBody = {
        "tierName": animalName,
        "tierAlter": animalAge,
        "artId": animalType
    };

    // Send the POST request
    fetch('http://localhost:8080/tiere', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(function(response) {
            if (response.ok) {
                alert('Animal added successfully');
                animalTypeInput.value="";
                animalNameInput.value="";
                animalAgeInput.value="";
                fetchTiers()
            } else {
                alert('Failed to add animal');
            }
        })
        .catch(function(error) {
            console.log('Error:', error);
        });
}

//Update
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("updateForm").addEventListener("submit", updateAnimal);
});

function updateAnimal(event) {
    event.preventDefault();

    var animalId = document.getElementById("animalId").value;
    var updatedAnimalName = document.getElementById("updatedAnimalName").value;
    var updatedAnimalType = document.getElementById("updatedAnimalType").value;
    var updatedAnimalAge = document.getElementById("updatedAnimalAge").value;
    var animalTypeInput = document.getElementById("updatedAnimalType");
    var animalNameInput = document.getElementById("updatedAnimalName");
    var animalAgeInput = document.getElementById("updatedAnimalAge");
    var animalIdInput = document.getElementById("animalId");

    //Validate
    if (animalId < 1) {
        alert("Please enter a valid ID.");
        animalIdInput.value="";
        return;
    }

    fetch('http://localhost:8080/tiere/' + animalId)
        .then(function(response) {
            if (response.ok) {
        if (updatedAnimalName.trim() === "" && updatedAnimalAge.trim() === "" && updatedAnimalType.trim() === "") {
            alert("No changes has been done");
        }else{

            // Create request body as JSON object
            var requestBody = {
                "artId": updatedAnimalType
            };

            if (updatedAnimalName.trim() !== "") {
                requestBody["tierName"] = updatedAnimalName;
            }

            if (updatedAnimalAge.trim() !== "") {
                if (updatedAnimalAge < 1) {
                    alert("Please enter a valid age.");
                    animalAgeInput.value="";
                    return;
                }else{
                    requestBody["tierAlter"] = updatedAnimalAge;
                }
            }

            // Send request
            fetch('http://localhost:8080/tiere/' + animalId, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestBody)
            })
                .then(function(response) {
                    if (response.ok) {
                        alert('Animal updated successfully');
                        animalTypeInput.value="";
                        animalNameInput.value="";
                        animalAgeInput.value="";
                        animalIdInput.value="";
                        fetchTiers()
                    } else {
                        alert('Failed to update animal');
                    }
                })
                .catch(function(error) {
                    console.log('Error:', error);
                });
        }
    }else{
        // Animal ID does not exist
        alert('There is no animal with this ID.');
        animalIdInput.value = "";
        animalTypeInput.value="";
        animalNameInput.value="";
        animalAgeInput.value="";
    }})
    .catch(function(error) {
        console.log('Error:', error);
    });
}

//Delete
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("deleteForm").addEventListener("submit", deleteAnimal);
});


function deleteAnimal(event) {
    event.preventDefault();

    var animalId = document.getElementById("deleteAnimalId").value;
    var animalIdInput = document.getElementById("deleteAnimalId");

    // Validate input values
    if (animalId < 1) {
        alert("Please enter a valid ID.");
        animalIdInput.value="";
        return;
    }

    fetch('http://localhost:8080/tiere/' + animalId)
        .then(function(response) {
            if (response.ok) {
        // Send the DELETE request
        fetch('http://localhost:8080/tiere/' + animalId, {
            method: 'DELETE'
        })
            .then(function(response) {
                if (response.ok) {
                    alert('Animal deleted successfully');
                    animalIdInput.value="";
                    fetchTiers()
                } else {
                    alert('Failed to delete animal');
                }
            })
            .catch(function(error) {
                console.log('Error:', error);
            });
    }else{
        // Animal ID does not exist
        alert('There is no animal with this ID.');
        animalIdInput.value = "";
    }})
    .catch(function(error) {
        console.log('Error:', error);
    });
}


//Get
/*document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("getterForm").addEventListener("submit", function(event) {
        event.preventDefault();
        fetchTiers();
    });
});*/
document.addEventListener("DOMContentLoaded", function() {
    const buttonElement = document.getElementById("get-button");
    const formElement = document.getElementById("getterForm");

    buttonElement.addEventListener("click", function() {
        formElement.style.display = "block";
        buttonElement.style.display = "none";
        fetchTiers();
    });
});


function fetchTiers() {
    const fetchData = { method: "get" };

    fetch('http://localhost:8080/tiere', fetchData)
        .then(response => {
            return response.json();
        })
        .then(jsonData => {
            showRecords(jsonData);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function showRecords(records) {
    const formContainer = document.getElementById('getterForm');

    // Remove existing list if present
    const existingList = document.getElementById('listContainer');
    if (existingList) {
        formContainer.removeChild(existingList);
    }

    // Create a new list container
    const newListContainer = document.createElement('div');
    newListContainer.id = 'listContainer';

    records.forEach(record => {
        const row = document.createElement('div');
        row.classList.add('form-row');

        const tierIdColumn = document.createElement('div');
        tierIdColumn.classList.add('col');
        tierIdColumn.textContent = record.tierId;

        const tierNameColumn = document.createElement('div');
        tierNameColumn.classList.add('col');
        tierNameColumn.textContent = record.tierName;

        const tierAlterColumn = document.createElement('div');
        tierAlterColumn.classList.add('col');
        tierAlterColumn.textContent = record.tierAlter;

        const artIdColumn = document.createElement('div');
        artIdColumn.classList.add('col');
        artIdColumn.textContent = record.artId;

        row.appendChild(tierIdColumn);
        row.appendChild(tierNameColumn);
        row.appendChild(tierAlterColumn);
        row.appendChild(artIdColumn);

        newListContainer.appendChild(row);
    });
    formContainer.insertBefore(newListContainer, formContainer.lastElementChild);
}

//Get Art
document.addEventListener("DOMContentLoaded", function() {
    const formElement = document.getElementById("artForm");
    const texto = document.getElementById("texto");
    const logoutButton = document.getElementById("logout");

    //Button Submit
    formElement.addEventListener("submit", function(event) {
        event.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        validateCredentials(username, password);
    });

    //Button logout
    logoutButton.addEventListener("click", function(event) {
        event.preventDefault();
        resetForm();
    });

    function validateCredentials(username, password) {
        const fetchData = {
            method: "get",
            headers: {
                "Authorization": "Basic " + btoa(username + ":" + password)
            }
        };

        fetch('http://localhost:8080/art', fetchData)
            .then(response => {
                if (response.ok) {
                    fetchArt();
                } else {
                    throw new Error('Invalid username or password.');
                }
            })
            .catch(error => {
                texto.textContent = error.message;
                texto.style.display = 'block';
            });
    }

    function fetchArt() {
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;
        const fetchData = { method: "get",
            headers: {
                "Authorization": "Basic " + btoa(username + ":" + password)
            }};

        fetch('http://localhost:8080/art', fetchData)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error: ' + response.status);
                }
            })
            .then(jsonData => {
                showArt(jsonData);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function showArt(artData) {
        const formContainerArt = document.getElementById('artForm');

        // Remove existing artResult element if it exists
        const existingArtResult = document.getElementById('artResult');
        if (existingArtResult) {
            existingArtResult.remove();
        }

        // Create a new list container
        const newListContainerArt = document.createElement('div');
        newListContainerArt.id = 'artResult';

        artData.forEach(artData => {
            const row = document.createElement('div');
            row.classList.add('form-row');

            const artIdColumn = document.createElement('div');
            artIdColumn.classList.add('col');
            artIdColumn.textContent = artData.artId;

            const tierartColumn = document.createElement('div');
            tierartColumn.classList.add('col');
            tierartColumn.textContent = artData.arten;

            row.appendChild(artIdColumn);
            row.appendChild(tierartColumn);

            newListContainerArt.appendChild(row);
        });
        formContainerArt.insertBefore(newListContainerArt, formContainerArt.lastElementChild);
        document.getElementById('sub').style.display = 'none';
        texto.style.display = 'none';
        document.getElementById('1').style.display = 'block';
        document.getElementById('2').style.display = 'block';
        document.getElementById('securityName').style.display = 'none';
        document.getElementById('securityPsw').style.display = 'none';
        document.getElementById('logout').style.display = 'block';
    }

    //If Logout Button pressed in get Art
    function resetForm() {
        // Clear input fields
        document.getElementById("username").value = "";
        document.getElementById("password").value = "";

        // Remove artResult element if it exists
        const existingArtResult = document.getElementById('artResult');
        if (existingArtResult) {
            existingArtResult.remove();
        }

        // Reset display styles
        document.getElementById('sub').style.display = 'block';
        document.getElementById('1').style.display = 'none';
        document.getElementById('2').style.display = 'none';
        document.getElementById('securityName').style.display = 'block';
        document.getElementById('securityPsw').style.display = 'block';
        logoutButton.style.display = 'none';
    }
});


