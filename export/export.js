const firestoreService = require('firestore-export-import');
const serviceAccount = require('./firestore_key.json');

// Initiate Firebase App
firestoreService.initializeApp(serviceAccount, "https://<YOU_DATABASE>.firebaseio.com");

// Start importing your data
// The array of date fields is optional
firestoreService.restore("./json/Tasks.json", ['created']);