/// <reference path="/yui_3.15.0/build/yui/yui-min.js"/>


YUI({filter:"raw"}).use("uploader", function(Y) {
   Y.one("#overallProgress").set("text", "Uploader type: " + Y.Uploader.TYPE);
   if (Y.Uploader.TYPE != "none" && !Y.UA.ios) {
       var uploader = new Y.Uploader({width: "250px",
                                      height: "35px",
                                      fileFieldName: "upload",
                                      multipleFiles: false,
                                      swfURL: "flashuploader.swf?t=" + Math.random(),
                                      uploadURL: "upload-bxx?dir="+Y.one("#dir").get('value'),
                                      simLimit: 2,
                                      withCredentials: false
                                     });
//       alert("uploadURL get:"+uploader.get('uploadURL')); 
       var uploadDone = false;
       
       console.log(uploader.get("postVarsPerFile"));

       uploader.render("#selectFilesButtonContainer");

       uploader.after("fileselect", function (event) {

          var fileList = event.fileList;
          var fileTable = Y.one("#filenames tbody");
          if (fileList.length > 0 && Y.one("#nofiles")) {
            Y.one("#nofiles").remove();
          }

          if (uploadDone) {
            uploadDone = false;
            fileTable.setHTML("");
          }

          Y.each(fileList, function (fileInstance) {
              fileTable.append("<tr id='" + fileInstance.get("id") + "_row" + "'>" +
                                    "<td class='filename'>" + fileInstance.get("name") + "</td>" +
                                    "<td class='filesize'>" + fileInstance.get("size") + "</td>" +
                                    "<td class='percentdone'>Hasn't started yet</td>");
                             });
       });

       uploader.on("uploadprogress", function (event) {
            var fileRow = Y.one("#" + event.file.get("id") + "_row");
                fileRow.one(".percentdone").set("text", event.percentLoaded + "%");
       });

       uploader.on("uploadstart", function (event) {
            uploader.set("enabled", false);
            
            Y.one("#uploadFilesButton").addClass("yui3-button-disabled");
            Y.one("#uploadFilesButton").detach("click");
       });

       uploader.on("uploadcomplete", function (event) {
            var fileRow = Y.one("#" + event.file.get("id") + "_row");
                fileRow.one(".percentdone").set("text", "Finished!");
            //niko
            //alert("going to "+Y.one("#dir").get('value'));
            location = "filelist-bxx?dir="+Y.one("#dir").get('value');
       });

       uploader.on("totaluploadprogress", function (event) {
                Y.one("#overallProgress").setHTML("Total uploaded: <strong>" + event.percentLoaded + "%" + "</strong>");
       });

       uploader.on("alluploadscomplete", function (event) {
                     uploader.set("enabled", true);
                     uploader.set("fileList", []);
                     Y.one("#uploadFilesButton").removeClass("yui3-button-disabled");
                     Y.one("#uploadFilesButton").on("click", function () {
                          if (!uploadDone && uploader.get("fileList").length > 0) {
                             uploader.uploadAll();
                          }
                     });
                     Y.one("#overallProgress").set("text", "Uploads complete!");
                     uploadDone = true;
       });

       Y.one("#uploadFilesButton").on("click", function () {
         if (!uploadDone && uploader.get("fileList").length > 0) {
        	 
        	uploader.set("postVarsPerFile", {'tags':''+Y.one("#tags").get("value")+''});
            uploader.uploadAll();
         }
       });
   }
   else {
       Y.one("#uploaderContainer").set("text", "We are sorry, but to use the uploader, you either need a browser that support HTML5 or have the Flash player installed on your computer.");
   }


});
