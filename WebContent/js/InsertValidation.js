
function validateForm() {
    var youtubeUrl = document.getElementById("youtubeUrl").value;
    var title = document.getElementById("title").value;
    var category = document.getElementById("category").value;

  
    var youtubePattern = /^(https?:\/\/)?(www\.)?(youtube\.com|youtu\.?be)\/.+$/;

  
    if (title.trim() === "") {
        alert("Title is required.");
        return false;
    }

 
    if (!youtubePattern.test(youtubeUrl)) {
        alert("Please enter a valid YouTube URL.");
        return false;
    }

   
    if (category === "") {
        alert("Please select a category.");
        return false;
    }

    return true;
}

