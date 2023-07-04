export function hasPermission(perms) {

    let hasPermission = false;
    // console.log(localStorage.getItem('userAuth'));
    let permission = localStorage.getItem('userAuth');
    // console.log(permission);
    let index = permission.indexOf(perms);
    // console.log(index);
    if (index > 0 || index == 0) {
      hasPermission = true;
    }
    return hasPermission;
  }