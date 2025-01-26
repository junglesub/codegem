import { atom } from "recoil";

const localStorageEffect =
  (key) =>
  ({ setSelf, onSet }) => {
    const savedValue = localStorage.getItem(key);
    if (savedValue != null) {
      setSelf(JSON.parse(savedValue));
    }

    onSet((newValue, _, isReset) => {
      isReset
        ? localStorage.removeItem(key)
        : localStorage.setItem(key, JSON.stringify(newValue));
    });

    // When the value changes in localstorage (from another tab), update the atom
    const handleStorageChange = (event) => {
      if (event.key === key) {
        if (event.newValue === null) {
          resetSelf();
        } else {
          const newValue = JSON.parse(event.newValue);
          setSelf(newValue);
        }
      }
    };
    window.addEventListener("storage", handleStorageChange);
    return () => {
      window.removeEventListener("storage", handleStorageChange);
    };
  };

export const authJwtAtom = atom({
  key: "gh_token",
  default: "",
  effects: [localStorageEffect("code_gem_gh_token")],
});
