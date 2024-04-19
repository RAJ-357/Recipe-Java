const fetchRecipes = async () => {
  try {
    // Retrieve the JSON data from localStorage
    const jsonData = localStorage.getItem('recipesResponse');
    
    if (!jsonData) {
      console.error('Error fetching recipes: Data not found in localStorage');
      return [];
    }

    // Parse the JSON data
    const data = JSON.parse(jsonData);
    console.log(data.results);
    
    // Return only the 'results' array from the parsed JSON data
    return data.results;
  } catch (error) {
    console.error('Error fetching recipes:', error);
    return []; // Handle error gracefully, return empty array for now
  }
};

const renderRecipes = async (recipes) => {
  const recipeContainer = document.querySelector('.recipes-container');
  recipeContainer.innerHTML = ''; // Clear existing content

  if (recipes.length === 0) {
    const noRecipesMessage = document.createElement('p');
    noRecipesMessage.textContent = 'No recipes found.';
    recipeContainer.appendChild(noRecipesMessage);
    return; // Exit the function early if no recipes are found
  }

  recipes.forEach(async recipe => {
    const card = document.createElement('div');
    card.classList.add('recipe-card');

    const image = document.createElement('img');
    image.src = recipe.image;
    image.alt = 'Recipe Image';

    const id = document.createElement('span');
    id.textContent = `ID: ${recipe.id}`;
    id.classList.add('recipe-id');

    const title = document.createElement('h3');
    title.textContent = recipe.title;

    const nutrientsList = document.createElement('ul');
    recipe.nutrition.nutrients.forEach(nutrient => {
      const nutrientItem = document.createElement('li');
      nutrientItem.textContent = `${nutrient.name}: ${nutrient.amount} ${nutrient.unit}`;
      nutrientsList.appendChild(nutrientItem);
    });

    card.addEventListener('click', () => {
        localStorage.setItem('recipeId', recipe.id);
    });

    card.appendChild(image);
    card.appendChild(title);
    card.appendChild(id);
    card.appendChild(nutrientsList);

    recipeContainer.appendChild(card);
  });
};


(async () => {
  const recipes = await fetchRecipes();
  renderRecipes(recipes);
})();

