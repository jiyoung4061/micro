/*
    3-4. index.js
        이 파일 추가 후, footer.mustache파일에 이 script를 추가해야 한다. -- C1

    3-8. btn-update를 클릭했을 때, update()를 호출하고, update() 정의
        이때 update는 반드시 PUT method를 사용한다.
        indexController에 가서 update 기능을 확인.

    3-9. delete 기능 수행 btn-delete
        delete할 때는 DELETE method
        PostsService에서 delete() 추가
*/

var main = {
      init : function () {
          var _this = this;
          $('#btn-save').on('click', function () {
              _this.save();
          });

         $('#btn-update').on('click', function () {
            _this.update();
         });

         $('#btn-delete').on('click', function () {
             _this.delete();
          });
      },
      save : function () {
          var data = {
              title: $('#title').val(),
              author: $('#author').val(), // var author = document.getElementById('author').value;
              content: $('#content').val()
          };

          $.ajax({
              type: 'POST',
              url: '/api/v1/posts',
              dataType: 'json',
              contentType: 'application/json; charset=utf-8',
              data: JSON.stringify(data)
          }).done(function() {
              alert('글이 등록되었습니다.');
              window.location.href = '/';
          }).fail(function (error) {
              alert(JSON.stringify(error));
          })
      } ,
      update : function () {
          var data = {
              title: $('#title').val(),
              content: $('#content').val()
          };

          var id = $('#id').val();

          $.ajax({
              type: 'PUT',
              url: '/api/v1/posts/'+id,
              dataType: 'json',
              contentType: 'application/json; charset=utf-8',
              data: JSON.stringify(data)
          }).done(function() {
              alert('글이 변경되었습니다.');
              window.location.href = '/';
          }).fail(function (error) {
              alert(JSON.stringify(error));
          })
      } ,
     delete : function () {
         var id = $('#id').val();

         $.ajax({
             type: 'DELETE',
             url: '/api/v1/posts/'+id,
             dataType: 'json',
             contentType: 'application/json; charset=utf-8'
         }).done(function() {
             alert('글이 삭제되었습니다.');
             window.location.href = '/';
         }).fail(function (error) {
             alert(JSON.stringify(error));
         })
     }
  }
  main.init();
