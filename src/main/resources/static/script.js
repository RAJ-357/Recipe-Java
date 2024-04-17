document.addEventListener('DOMContentLoaded', () => {
    const recipeList = document.getElementById('recipeList');
    
    fetch('/recipes/getRecipes')
      .then(response => response.json())
      .then(recipes => {
        recipes.forEach(recipe => {
          const li = document.createElement('li');
          li.textContent = recipe.title;
  
          const detailsDiv = document.createElement('div');
          const button = document.createElement('button')
          button.textContent = "Details"
          detailsDiv.classList.add('recipe-details');
          detailsDiv.innerHTML = `
            <p>ID: ${recipe.id}</p>
            <p>Ingredients: ${recipe.ingredients}</p>
            <p>Instructions: ${recipe.instructions}</p>
            <p>Cooking Time: ${recipe.cookingTime} mins</p>
            <p>Difficulty Level: ${recipe.difficultyLevel}</p>
            <p>Likes: ${recipe.likes}</p>
            <button class="like-btn" data-title="${recipe.title}">Like</button>
          `;
          
          button.addEventListener('click', () => {
            detailsDiv.style.display = detailsDiv.style.display === 'none' ? 'block' : 'none';
          });
  
          li.appendChild(detailsDiv);
          li.appendChild(button);
  
          recipeList.appendChild(li);
        });
      })
      .catch(error => console.error('Error fetching recipes:', error));
  
    // Handle like button click
    recipeList.addEventListener('click', event => {
      if (event.target.classList.contains('like-btn')) {
        const recipeTitle = event.target.dataset.title;
  
        fetch('/recipes/like', { 
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: recipeTitle
          })
          .catch(error => console.error('Error processing like:', error));
      }
    });
  });
  
  