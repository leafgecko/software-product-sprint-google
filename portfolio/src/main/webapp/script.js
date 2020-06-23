// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/* Adds a random quote to the page */
function addRandomQuote() {
    const quotes =
        ['We cannot change the cards we are dealt, just how we play the hand.', 
        'When it comes to men who are romantically interested in you, it’s really simple. Just ignore everything they say and only pay attention to what they do.', 
        'The key question to keep asking is, Are you spending your time on the right things? Because time is all you have.', 
        'Showing gratitude is one of the simplest yet most powerful things humans can do for each other.'];

    // Pick a random quote.
    const quote = quotes[Math.floor(Math.random() * quotes.length)];

    // Add it to the page.
    const quoteContainer = document.getElementById('quote-container');
    if (quoteContainer == null) {return} // no-op
    quoteContainer.innerText = quote;
}

/* Toggle between adding and removing the "responsive" class to topnav when the user clicks on the icon */
function onNavClick() {
    var htmlELement = document.getElementById("my-topnav");
    if (htmlELement == null) {return} // no-op
    if (htmlELement.className === "topnav") {
        htmlELement.className += " responsive";
    } else {
        htmlELementx.className = "topnav";
    }
}

/* Adds one more quote to the page */
function addMoreQuotes() {
  fetch('/more-quotes').then(response => response.text()).then((quote) => {
    document.getElementById('more-quotes-container').innerText = quote;
  });
}

/* Show favourite Pokemon to the page */
function showFavouritePokemon() {
    fetch('/data').then(response => response.json()) // parses the respoonse as JSON
    .then((favPokemon) => { // now we can reference the fields in favPokemon!
        console.log(favPokemon);
        document.getElementById('pokemon-container').innerText = favPokemon;
    });
}

/* Show comments to the page */
function showComments() {
    fetch('/comment').then(response => response.json()) // parses the respoonse as JSON
    .then((commentList) => { // now we can reference the fields in commentList!
        console.log(commentList);
        // document.getElementById('comment-container').innerText = commentList;
        const commentContainer = document.getElementById('comment-container')
        commentList.forEach((comment) => {
            commentContainer.appendChild(createCommentElement(comment));
        })
    });
}

/* Creates an element that represents a comment */
function createCommentElement(comment) {
    const commentElement = document.createElement('li');
    commentElement.className = "comment";

    const textElement = document.createElement('span');
    textElement.innerText = comment.text;

    commentElement.appendChild(textElement);
    return commentElement;
}
