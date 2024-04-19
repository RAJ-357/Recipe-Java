// document.addEventListener("DOMContentLoaded", function () {
//     try {
//         const data = JSON.parse(localStorage.getItem('recipeDetails'));
//         console.log("here");
//         // Populate ingredients
//         const ingredientsContainer = document.getElementById('ingredients-container');
//         const ingredientsList = data.extendedIngredients.map(ingredient => `<li>${ingredient.original}</li>`).join('');
//         ingredientsContainer.innerHTML = `<ul>${ingredientsList}</ul>`;

//         // Populate instructions
//         const instructionsContainer = document.getElementById('instructions-container');
//         instructionsContainer.innerHTML = data.instructions;
//     } catch (error) {
//         console.error('Error parsing or retrieving data from local storage:', error);
//     }
// });


const fetchDetails = async () => {
    try {
      // Retrieve the JSON data from localStorage
      const jsonData = localStorage.getItem('recipeDetails');
      
      if (!jsonData) {
        console.error('Error fetching recipes: Data not found in localStorage');
        return [];
      }
  
      // Parse the JSON data
      const data = JSON.parse(jsonData);
      console.log(data);
      
      // Return only the 'results' array from the parsed JSON data
      return data;
    } catch (error) {
      console.error('Error fetching recipes:', error);
      return []; // Handle error gracefully, return empty array for now
    }
  };
  
const renderDetails = async (details) => {
    
    const ingredientsContainer = document.getElementById('ingredients-container');
    const instructionsContainer = document.getElementById('instructions-container');
    if (details.length === 0) {
      const noRecipesMessage = document.createElement('p');
      noRecipesMessage.textContent = 'No details found.';
      ingredientsContainer.appendChild(noRecipesMessage);
      return; // Exit the function early if no details are found
    }
  
    const ingredientsList = document.createElement('ul');
    details.extendedIngredients.forEach(ingredient => {
        const li = document.createElement('li');
        li.textContent = ingredient.original;
        ingredientsList.appendChild(li);
    });
    ingredientsContainer.appendChild(ingredientsList);

    const instructionsParagraph = document.createElement('p');
    instructionsParagraph.textContent = details.instructions;
    instructionsContainer.appendChild(instructionsParagraph);
  };
  
  
  (async () => {
    const details = await fetchDetails();
    renderDetails(details);
  })();
  
  
