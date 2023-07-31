function solution(userId, bannedId) {
  const N = bannedId.length;
  const M = userId.length;
  const set = new Set();
  const isVisited = Array(userId.length).fill(false);

  // 제재 아이디와 일치하는지 확인
  const isBannedUser = (user, form) => {
    if (user.length !== form.length) return false;
    for (let i = 0; i < user.length; i++) {
      if (form[i] === "*") continue;
      if (form[i] !== user[i]) return false;
    }
    return true;
  };

  // 탐색
  const dfs = (idx, foundUsers) => {
    // 제재 아이디를 모두 찾을 때까지
    if (idx === N) {
      set.add(foundUsers.sort().join(""));
      return;
    }

    for (let i = 0; i < M; i++) {
      if (isVisited[i]) continue;

      if (isBannedUser(userId[i], bannedId[idx])) {
        isVisited[i] = true;
        dfs(idx + 1, [...foundUsers, userId[i]]);
        isVisited[i] = false;
      }
    }
  };

  dfs(0, []);
  return set.size;
}
