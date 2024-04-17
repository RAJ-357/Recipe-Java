const form = document.getElementById('meal-form');
const submitButton = form.querySelector('button');

// Add event listener to the submit button
submitButton.addEventListener('click', handleFormSubmit);

// Function to handle form submission
function handleFormSubmit(event) {
  event.preventDefault(); // Prevent default form submission behavior

  // Get all selected checkboxes and their corresponding min/max values
  const selectedIngredients = {};
  const ingredientGroups = form.querySelectorAll('.ingredient-group');
  for (const group of ingredientGroups) {
    const checkbox = group.querySelector('input[type="checkbox"]');
    if (checkbox.checked) {
      const name = checkbox.name;
      const minInput = group.querySelector(`input[name="${name}_min"]`);
      const maxInput = group.querySelector(`input[name="${name}_max"]`);
      const min = parseInt(minInput.value, 10) || 0;
      const max = parseInt(maxInput.value, 10) || 0;
      selectedIngredients[name] = { min, max };
    }
  }

  // You can now process the selectedIngredients object further
  // For example, send it to a server for storage or display it on the page
  console.log('Selected ingredients:', selectedIngredients);
}
