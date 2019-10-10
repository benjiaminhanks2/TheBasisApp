# Getting Started


##### Basic Explained

| architecture      | MVVM                   |
|---                | ---                           |
| Model             | Response       |
| View              | Activity    |
| ViewModel         | Business Logic                |

##### App Structure Explained
     Model            
     
     1. DataProviderClass- This provides the data to viewModel 
   
     View           
     
     1. MainActivity- Launcher activity
  
     View Model
     
     1. FlightViewModel- Business logic for getting data from api, sending it to activity which in turns sends it to adpater by binding it with CardViewModel
    
     
#### Steps to Install app
1. Copy apk from apk of folder of project and paste in your local
2. Run command on terminal adb install apk-file path
3. or install manually by downloading apk in your device
     


