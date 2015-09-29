(function () {
 CKEDITOR.plugins.add('UploadMovie',
{    init: function(editor)
    {
        var pluginName = 'UploadMovie';
        CKEDITOR.dialog.add(pluginName, this.path + 'dialogs/UploadMovie.js');
        editor.addCommand(pluginName, new CKEDITOR.dialogCommand(pluginName));
        editor.ui.addButton('UploadMovie',
            {
                label: '插入优酷视频地址',
                command: pluginName,
				icon: CKEDITOR.plugins.getPath('UploadMovie') + 'uploadmovie.png'
          });
    }
}); 
})();