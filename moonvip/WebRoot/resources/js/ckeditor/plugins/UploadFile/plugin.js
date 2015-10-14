(function () {
 CKEDITOR.plugins.add('UploadFile',
{    init: function(editor)
    {
        var pluginName = 'UploadFile';
        CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/UploadFile.js');
        editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));
        editor.ui.addButton('UploadFile',
            {
                label: '上传文件',
                command: pluginName,
				icon: CKEDITOR.plugins.getPath('UploadFile') + 'uploadfile.png'
          });
    }
}); 
})();