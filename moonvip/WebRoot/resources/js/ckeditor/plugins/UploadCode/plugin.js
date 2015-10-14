(function () {
 CKEDITOR.plugins.add('UploadCode',
{    init: function(editor)
    {
        var pluginName = 'UploadCode';
        CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/UploadCode.js');
        editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));
        editor.ui.addButton('UploadCode',
            {
                label: '上传代码',
                command: pluginName,
				icon: CKEDITOR.plugins.getPath('UploadCode') + 'uploadcode.png'
          });
    }
}); 
})();