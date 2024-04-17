const buttons = document.querySelectorAll('.box');

buttons.forEach(button => {
  button.addEventListener('click', () => {
    const link = button.dataset.link; // Get the data-link attribute value
    if (link) {
      window.location.href = link; // Redirect to the linked page
    }
  });
});
