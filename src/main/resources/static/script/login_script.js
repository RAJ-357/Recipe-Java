document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault();
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    // Here you can perform your login logic, such as sending an AJAX request to the server
    
    if (username === "a" && password === "a") {
      // Successful login
      alert("Login successful!");
      window.location.href = "Home.html";
      // Redirect to another page or perform any desired action
    } else {
      // Failed login
      document.getElementById("error-message").textContent = "Invalid username or password.";
    }
  });
  